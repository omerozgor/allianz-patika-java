package com.allianz.erp.service;

import com.allianz.erp.entity.concretes.Customer;
import com.allianz.erp.model.dto.CreateCustomerDTO;
import com.allianz.erp.model.dto.ResponseCustomerDTO;
import com.allianz.erp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerBaseRepository;

    public List<ResponseCustomerDTO> getAll(){
        List<Customer> customerList = customerBaseRepository.findAll();
        List<ResponseCustomerDTO> customerDTOList = customerList.stream()
                .map(customer -> new ResponseCustomerDTO(customer.getName(), customer.getAddress(),
                        customer.getCity(),customer.getPostalCode(),customer.getCountry()))
                .collect(Collectors.toList());

        return customerDTOList;
    }

    public Customer add(CreateCustomerDTO createCustomerDTO){
        Customer customer = new Customer();
        customer.setName(createCustomerDTO.getName());
        customer.setAddress(createCustomerDTO.getAddress());
        customer.setCity(createCustomerDTO.getCity());
        customer.setPostalCode(createCustomerDTO.getPostalCode());
        customer.setCountry(createCustomerDTO.getCountry());
        customerBaseRepository.save(customer);
        return customer;
    }

    public Customer update(UUID uuid,CreateCustomerDTO createCustomerDTO){
        Optional<Customer> optionalCustomer = customerBaseRepository.findByUuid(uuid);
        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customer.setName(createCustomerDTO.getName());
            customer.setCountry(createCustomerDTO.getCountry());
            customer.setCity(createCustomerDTO.getCity());
            customer.setAddress(createCustomerDTO.getAddress());
            customer.setPostalCode(createCustomerDTO.getPostalCode());
            customerBaseRepository.save(customer);
            return customer;
        }else {
            return null;
        }
    }

    public Customer getById(int id){
        Optional<Customer> optionalCustomer = customerBaseRepository.findById(id);
        return optionalCustomer.orElse(null);

    }

    public ResponseCustomerDTO getByUuid(UUID uuid){
        Optional<Customer> optionalCustomer = customerBaseRepository.findByUuid(uuid);
        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            ResponseCustomerDTO responseCustomerDTO = new ResponseCustomerDTO();
            responseCustomerDTO.setName(customer.getName());
            responseCustomerDTO.setCity(customer.getCity());
            responseCustomerDTO.setAddress(customer.getAddress());
            responseCustomerDTO.setCountry(customer.getCountry());
            responseCustomerDTO.setPostalCode(customer.getPostalCode());
            return  responseCustomerDTO;
        }else {
            return null;
        }

    }

    public void deleteByUuid(UUID uuid){
        Optional<Customer> optionalCustomer = customerBaseRepository.findByUuid(uuid);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customerBaseRepository.deleteById(customer.getId());
        }
    }


}
