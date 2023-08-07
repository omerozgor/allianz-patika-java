package com.allianz.erp.model.dto;


import com.allianz.erp.model.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDTO {


    private String customerName;

    private OrderStatusEnum orderStatus;

    private List<ResponseOrderDetailDTO> orderDetails;

}
