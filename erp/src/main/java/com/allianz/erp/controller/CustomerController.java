package com.allianz.erp.controller;

import com.allianz.erp.entity.concretes.Customer;
import com.allianz.erp.model.dto.CreateCustomerDTO;
import com.allianz.erp.model.dto.ResponseCustomerDTO;
import com.allianz.erp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("getall")
    public ResponseEntity<List<ResponseCustomerDTO>> getAll(){

        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Customer> add(@RequestBody CreateCustomerDTO createCustomerDTO){
        return new ResponseEntity<>(customerService.add(createCustomerDTO),HttpStatus.CREATED);
    }

    @GetMapping("get-by-uuid/{uuid}")
    public ResponseEntity<ResponseCustomerDTO> getByUuid(@PathVariable UUID uuid){
        if (customerService.getByUuid(uuid) != null){
            return new ResponseEntity<>(customerService.getByUuid(uuid),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("update-by-uuid/{uuid}")
    public ResponseEntity<Customer> update(@PathVariable UUID uuid,@RequestBody CreateCustomerDTO createCustomerDTO){
        if (customerService.update(uuid,createCustomerDTO) != null){
            return new ResponseEntity<>(customerService.update(uuid,createCustomerDTO),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete-by-uuid/{uuid}")
    public void deleteByUuid(@PathVariable UUID uuid){
        customerService.deleteByUuid(uuid);
    }

}
