package com.allianz.erp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDetailDTO {

    private String productName;
    private int quantity;
}
