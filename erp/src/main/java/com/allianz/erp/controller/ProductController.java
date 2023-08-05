package com.allianz.erp.controller;

import com.allianz.erp.entity.concretes.Customer;
import com.allianz.erp.entity.concretes.Product;
import com.allianz.erp.service.CustomerService;
import com.allianz.erp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {



    @Autowired
    ProductService productService;

    @GetMapping("getall")
    public ResponseEntity<List<Product>> getAll(){

        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Product> add(@RequestBody Product product){
        return new ResponseEntity<>(productService.add(product),HttpStatus.CREATED);
    }

}
