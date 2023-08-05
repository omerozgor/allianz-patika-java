package com.allianz.erp.service;

import com.allianz.erp.entity.concretes.Customer;
import com.allianz.erp.entity.concretes.Product;
import com.allianz.erp.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    BaseRepository<Product> productBaseRepository;

    public List<Product> getAll(){
        return productBaseRepository.findAll();
    }

    public Product add(Product product){

        productBaseRepository.save(product);
        return product;
    }

}
