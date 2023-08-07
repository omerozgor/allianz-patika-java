package com.allianz.erp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrderDetailDTO {

    private int productId;
    private int quantity;
}
