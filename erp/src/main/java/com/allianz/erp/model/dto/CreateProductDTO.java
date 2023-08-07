package com.allianz.erp.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductDTO {
    private String name;
    private boolean isKdvApplied;
    private BigDecimal priceWithoutKdv;
    private int stockAmount;
}
