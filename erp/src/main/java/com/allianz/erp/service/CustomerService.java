package com.allianz.erp.service;

import com.allianz.erp.entity.concretes.Customer;
import com.allianz.erp.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    BaseRepository<Customer> customerBaseRepository;

    public List<Customer> getAll(){
        return customerBaseRepository.findAll();
    }

    public Customer add(Customer customer){

        customerBaseRepository.save(customer);
        return customer;
    }

}
