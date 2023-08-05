package com.allianz.erp.controller;

import com.allianz.erp.entity.concretes.Customer;
import com.allianz.erp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("getall")
    public ResponseEntity<List<Customer>> getAll(){

        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Customer> add(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.add(customer),HttpStatus.CREATED);
    }

}
