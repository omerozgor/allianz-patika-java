package com.allianz.erp.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDTO {
    private int customerId;
    List<CreateOrderDetailDTO> createOrderDetailDTOS;
}
