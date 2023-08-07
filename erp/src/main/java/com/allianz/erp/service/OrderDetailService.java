package com.allianz.erp.service;

import com.allianz.erp.entity.concretes.Order;
import com.allianz.erp.entity.concretes.OrderDetail;
import com.allianz.erp.model.dto.CreateOrderDetailDTO;
import com.allianz.erp.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailRepository orderDetailBaseRepository;


    public List<OrderDetail> getAll(){
        return orderDetailBaseRepository.findAll();
    }

    public void createOrderDetails(List<CreateOrderDetailDTO> createOrderDetailDTOList,int orderId){

        Order order = orderService.getById(orderId);
        List<OrderDetail> orderDetailList = createOrderDetailDTOList.stream()
                .map(createOrderDetailDTO -> new OrderDetail(order,
                        productService.getById(createOrderDetailDTO.getProductId()),
                        createOrderDetailDTO.getQuantity()))
                .collect(Collectors.toList());
        orderDetailBaseRepository.saveAll(orderDetailList);

    }

    public List<OrderDetail> getAllByOrderId(int orderId){
        return orderDetailBaseRepository.findAllByOrderId(orderId);
    }

    public void deleteAllByOrderId(int orderId){
        orderDetailBaseRepository.deleteAllByOrderId(orderId);
    }

}
