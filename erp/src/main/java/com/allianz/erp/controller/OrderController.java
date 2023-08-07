package com.allianz.erp.controller;

import com.allianz.erp.entity.concretes.Order;
import com.allianz.erp.model.dto.CreateOrderDTO;
import com.allianz.erp.model.dto.ResponseCustomerDTO;
import com.allianz.erp.model.dto.ResponseOrderDTO;
import com.allianz.erp.service.OrderDetailService;
import com.allianz.erp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;


    @GetMapping("getall")
    public ResponseEntity<List<ResponseOrderDTO>> getAll(){

        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("get-by-uuid/{uuid}")
    public ResponseEntity<ResponseOrderDTO> getByUuid(@PathVariable UUID uuid){
        if (orderService.getByUuid(uuid) != null){
            return new ResponseEntity<>(orderService.getByUuid(uuid),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("create")
    public ResponseEntity<CreateOrderDTO> createOrder(@RequestBody CreateOrderDTO orderDTO){
        Order order = orderService.createOrder(orderDTO.getCustomerId());
        orderDetailService.createOrderDetails(orderDTO.getCreateOrderDetailDTOS(),order.getId());
        return new ResponseEntity<>(orderDTO,HttpStatus.CREATED);
    }

    @PutMapping("confirm-order/{orderUuid}")
    public ResponseEntity<String> confirmOrder(@PathVariable UUID orderUuid){
        orderService.confirmOrder(orderUuid);
        return new ResponseEntity<>("The order has been confirmed",HttpStatus.OK);
    }
    @PutMapping("send-order-to-delivery/{orderUuid}")
    public ResponseEntity<String> sendOrderToDelivery(@PathVariable UUID orderUuid){
        orderService.sendOrderToDelivery(orderUuid);
        return new ResponseEntity<>("The order has been shipped",HttpStatus.OK);
    }

    @PutMapping("reject-order/{orderUuid}")
    public ResponseEntity<String> rejectOrder(@PathVariable UUID orderUuid){
        orderService.rejectOrder(orderUuid);
        return new ResponseEntity<>("The order has been rejected",HttpStatus.OK);
    }


    @DeleteMapping("delete-by-uuid/{uuid}")
    public void deleteByUuid(@PathVariable UUID uuid){
        orderService.deleteByUuid(uuid);
    }
}
