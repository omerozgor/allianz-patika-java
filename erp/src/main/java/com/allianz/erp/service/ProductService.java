package com.allianz.erp.service;

import com.allianz.erp.entity.concretes.Customer;
import com.allianz.erp.entity.concretes.Product;
import com.allianz.erp.model.dto.CreateProductDTO;
import com.allianz.erp.model.dto.ResponseCustomerDTO;
import com.allianz.erp.model.dto.ResponseProductDTO;
import com.allianz.erp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productBaseRepository;

    public List<ResponseProductDTO> getAll(){
        List<Product> productList = productBaseRepository.findAll();
        List<ResponseProductDTO> responseProductDTOList = productList.stream()
                .map(product -> new ResponseProductDTO(product.getName(), product.isKdvApplied(),
                        product.getPriceWithoutKdv(),product.getStockAmount()))
                .collect(Collectors.toList());
        return responseProductDTOList;
    }

    public Product add(CreateProductDTO createProductDTO){
        Product product = new Product();
        product.setName(createProductDTO.getName());
        product.setKdvApplied(createProductDTO.isKdvApplied());
        product.setStockAmount(createProductDTO.getStockAmount());
        product.setPriceWithoutKdv(createProductDTO.getPriceWithoutKdv());
        productBaseRepository.save(product);
        return product;
    }

    public Product getById(int id){
        Optional<Product> optionalProduct = productBaseRepository.findById(id);
        return optionalProduct.orElse(null);

    }
    public ResponseProductDTO getByUuid(UUID uuid){
        Optional<Product> optionalProduct = productBaseRepository.findByUuid(uuid);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            ResponseProductDTO responseProductDTO = new ResponseProductDTO();
            responseProductDTO.setName(product.getName());
            responseProductDTO.setStockAmount(product.getStockAmount());
            responseProductDTO.setKdvApplied(product.isKdvApplied());
            responseProductDTO.setPriceWithoutKdv(product.getPriceWithoutKdv());
            return  responseProductDTO;
        }else {
            return null;
        }

    }

    public void updateStockAmountById(int productId,int stockAmount){
        Optional<Product> optionalProduct = productBaseRepository.findById(productId);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setStockAmount(stockAmount);
            productBaseRepository.save(product);
        }
    }

    public void updatePriceByUuid(UUID productUuid, BigDecimal price){
        Optional<Product> optionalProduct = productBaseRepository.findByUuid(productUuid);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setPriceWithoutKdv(price);
            productBaseRepository.save(product);
        }
    }

    public void deleteByUuid(UUID uuid){
        Optional<Product> optionalProduct = productBaseRepository.findByUuid(uuid);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productBaseRepository.deleteById(product.getId());
        }
    }

}
