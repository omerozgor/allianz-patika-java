package com.allianz.erp.controller;

import com.allianz.erp.entity.concretes.Customer;
import com.allianz.erp.entity.concretes.Product;
import com.allianz.erp.model.dto.CreateProductDTO;
import com.allianz.erp.model.dto.ResponseCustomerDTO;
import com.allianz.erp.model.dto.ResponseProductDTO;
import com.allianz.erp.service.CustomerService;
import com.allianz.erp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/products")
public class ProductController {



    @Autowired
    ProductService productService;

    @GetMapping("getall")
    public ResponseEntity<List<ResponseProductDTO>> getAll(){

        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("get-by-uuid/{uuid}")
    public ResponseEntity<ResponseProductDTO> getByUuid(@PathVariable UUID uuid){
        if (productService.getByUuid(uuid) != null){
            return new ResponseEntity<>(productService.getByUuid(uuid),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping("add")
    public ResponseEntity<Product> add(@RequestBody CreateProductDTO createProductDTO){
        return new ResponseEntity<>(productService.add(createProductDTO),HttpStatus.CREATED);
    }

    @PutMapping("update-price-by-uuid/{productUuid}")
    public void updatePrice(@RequestBody BigDecimal price, @PathVariable UUID productUuid){

        productService.updatePriceByUuid(productUuid,price);

    }
    @DeleteMapping("delete-by-uuid/{uuid}")
    public void deleteByUuid(@PathVariable UUID uuid){
        productService.deleteByUuid(uuid);
    }

}
