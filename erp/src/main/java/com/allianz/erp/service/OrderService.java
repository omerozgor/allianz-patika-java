package com.allianz.erp.service;


import com.allianz.erp.entity.concretes.Order;
import com.allianz.erp.entity.concretes.OrderDetail;
import com.allianz.erp.entity.concretes.Product;
import com.allianz.erp.exceptions.IllegalOrderStatusException;
import com.allianz.erp.exceptions.OutOfStockException;
import com.allianz.erp.model.OrderStatusEnum;
import com.allianz.erp.model.dto.ResponseOrderDTO;
import com.allianz.erp.model.dto.ResponseOrderDetailDTO;
import com.allianz.erp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderBaseRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;
    @Autowired
    @Lazy
    OrderDetailService orderDetailService;

    @Autowired
    @Lazy
    BillService billService;

    @Transactional
    public List<ResponseOrderDTO> getAll() {
        List<Order> orderList = orderBaseRepository.findAll();

        List<ResponseOrderDTO> responseOrderDTOList = orderList.stream()
                .map(order -> new ResponseOrderDTO(order.getCustomer().getName(), order.getOrderStatus(),
                        order.getOrderDetails().stream().map(orderDetail -> {
                            ResponseOrderDetailDTO responseOrderDetailDTO = new ResponseOrderDetailDTO();
                            responseOrderDetailDTO.setProductName(orderDetail.getProduct().getName());
                            responseOrderDetailDTO.setQuantity(orderDetail.getQuantity());
                            return responseOrderDetailDTO;
                        }).collect(Collectors.toList())))
                .collect(Collectors.toList());
        return responseOrderDTOList;
    }

    public Order getById(int id) {
        Optional<Order> optionalOrder = orderBaseRepository.findById(id);
        return optionalOrder.orElse(null);

    }

    @Transactional
    public ResponseOrderDTO getByUuid(UUID uuid) {
        Optional<Order> optionalOrder = orderBaseRepository.findByUuid(uuid);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
            responseOrderDTO.setCustomerName(order.getCustomer().getName());
            responseOrderDTO.setOrderStatus(order.getOrderStatus());
            List<ResponseOrderDetailDTO> responseOrderDetailDTOList = order.getOrderDetails()
                    .stream().map(orderDetail ->
                            new ResponseOrderDetailDTO(orderDetail.getProduct().getName(), orderDetail.getQuantity()))
                    .collect(Collectors.toList());
            responseOrderDTO.setOrderDetails(responseOrderDetailDTOList);
            return responseOrderDTO;
        } else {
            return null;
        }
    }

    public Order createOrder(int customerId) {
        Order order = new Order();
        order.setCustomer(customerService.getById(customerId));
        return orderBaseRepository.save(order);
    }

    @Transactional
    public void confirmOrder(UUID orderUuid) {
        Optional<Order> optionalOrder = orderBaseRepository.findByUuid(orderUuid);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (!order.getOrderStatus().equals(OrderStatusEnum.WAITING_FOR_CONFIRMATION)) {
                throw new IllegalOrderStatusException("This order is not waiting for confirmation");
            } else {
                order.setOrderStatus(OrderStatusEnum.CONFIRMED);
                billService.createBill(order.getId());
                List<OrderDetail> orderDetailList = orderDetailService.getAllByOrderId(order.getId());
                for (OrderDetail orderDetail : orderDetailList) {
                    Product product = orderDetail.getProduct();
                    if (product.getStockAmount() - orderDetail.getQuantity() < 0) {
                        throw new OutOfStockException("Out of Stock");
                    } else {
                        productService.updateStockAmountById(product.getId(), product.getStockAmount() - orderDetail.getQuantity());
                    }

                }
                orderBaseRepository.save(order);
            }
        }

    }

    public void sendOrderToDelivery(UUID orderUuid) {
        Optional<Order> optionalOrder = orderBaseRepository.findByUuid(orderUuid);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (!order.getOrderStatus().equals(OrderStatusEnum.CONFIRMED)) {
                throw new IllegalOrderStatusException("Only confirmed orders can be sent to delivery");
            } else {
                order.setOrderStatus(OrderStatusEnum.SHIPPED);
                orderBaseRepository.save(order);
            }
        }
    }

    public void rejectOrder(UUID orderUuid) {
        Optional<Order> optionalOrder = orderBaseRepository.findByUuid(orderUuid);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (!order.getOrderStatus().equals(OrderStatusEnum.WAITING_FOR_CONFIRMATION)) {
                throw new IllegalOrderStatusException("This order can't be rejected");
            } else {
                order.setOrderStatus(OrderStatusEnum.REJECTED);
                orderBaseRepository.save(order);
            }
        }

    }

    @Transactional
    public void deleteByUuid(UUID uuid) {
        Optional<Order> optionalOrder = orderBaseRepository.findByUuid(uuid);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            orderDetailService.deleteAllByOrderId(order.getId());
            orderBaseRepository.deleteById(order.getId());
        }
    }

}
