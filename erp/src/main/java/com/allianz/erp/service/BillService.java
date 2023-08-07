package com.allianz.erp.service;

import com.allianz.erp.entity.concretes.Bill;
import com.allianz.erp.entity.concretes.Customer;
import com.allianz.erp.entity.concretes.Order;
import com.allianz.erp.entity.concretes.OrderDetail;
import com.allianz.erp.model.BillProduct;
import com.allianz.erp.model.dto.ResponseBillDTO;
import com.allianz.erp.model.dto.ResponseCustomerDTO;
import com.allianz.erp.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BillService {

    @Autowired
    BillRepository billRepository;
    @Autowired
    OrderService orderService;

    public List<ResponseBillDTO> getAll() {
        List<Bill> billList = billRepository.findAll();
        List<ResponseBillDTO> responseBillDTOList = billList.stream()
                .map(bill -> {
                    ResponseBillDTO responseBillDTO = new ResponseBillDTO();
                    responseBillDTO.setCustomerName(bill.getOrder().getCustomer().getName());
                    responseBillDTO.setTotalAmount(bill.getTotalAmount());
                    List<BillProduct> billProductList = new ArrayList<>();
                    for (OrderDetail orderDetail : bill.getOrder().getOrderDetails()
                    ) {
                        BillProduct billProduct = new BillProduct();
                        billProduct.setName(orderDetail.getProduct().getName());
                        billProduct.setQuantity(orderDetail.getQuantity());
                        billProduct.setPriceWithoutKdv(orderDetail.getProduct().getPriceWithoutKdv());
                        billProduct.setPriceWithKdv(billProduct.getPriceWithoutKdv().multiply(new BigDecimal(1.18)));
                        billProductList.add(billProduct);
                    }
                    responseBillDTO.setProducts(billProductList);
                    return responseBillDTO;
                }).collect(Collectors.toList());

        return responseBillDTOList;
    }

    public ResponseBillDTO getByUuid(UUID uuid){
        Optional<Bill> optionalBill = billRepository.findByUuid(uuid);
        if (optionalBill.isPresent()){
            Bill bill = optionalBill.get();
            ResponseBillDTO responseBillDTO = new ResponseBillDTO();
            List<BillProduct> billProductList = new ArrayList<>();
            for (OrderDetail orderDetail : bill.getOrder().getOrderDetails()
            ) {
                BillProduct billProduct = new BillProduct();
                billProduct.setName(orderDetail.getProduct().getName());
                billProduct.setQuantity(orderDetail.getQuantity());
                billProduct.setPriceWithoutKdv(orderDetail.getProduct().getPriceWithoutKdv());
                billProduct.setPriceWithKdv(billProduct.getPriceWithoutKdv().multiply(new BigDecimal(1.18)));
                billProductList.add(billProduct);
            }
            responseBillDTO.setProducts(billProductList);
            responseBillDTO.setCustomerName(bill.getOrder().getCustomer().getName());
            responseBillDTO.setTotalAmount(bill.getTotalAmount());
            return  responseBillDTO;
        }else {
            return null;
        }
    }

    public void createBill(int orderId) {
        Bill bill = new Bill();
        Order order = orderService.getById(orderId);
        bill.setOrder(orderService.getById(orderId));
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderDetail orderDetail:bill.getOrder().getOrderDetails()
             ) {
            totalAmount = totalAmount.add(orderDetail.getProduct().getPriceWithoutKdv().multiply(new BigDecimal(1.18)));
        }
        bill.setTotalAmount(totalAmount);
        billRepository.save(bill);

    }

}
