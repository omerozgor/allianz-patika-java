package com.allianz.erp.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDTO {


    private String name;

    private boolean isKdvApplied;

    private BigDecimal priceWithoutKdv;

    private int stockAmount;

}
