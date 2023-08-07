package com.allianz.erp.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateCustomerDTO {

    private String name;
    private String address;
    private String city;
    private String postalCode;
    private String country;

}
