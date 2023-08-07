package com.allianz.erp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCustomerDTO {

    private String name;

    private String address;

    private String city;

    private String postalCode;

    private String country;

}
