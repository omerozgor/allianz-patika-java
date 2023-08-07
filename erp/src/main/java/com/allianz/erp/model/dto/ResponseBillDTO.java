package com.allianz.erp.model.dto;

import com.allianz.erp.model.BillProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBillDTO {

    private String customerName;
    List<BillProduct> products;

    BigDecimal totalAmount;
}
