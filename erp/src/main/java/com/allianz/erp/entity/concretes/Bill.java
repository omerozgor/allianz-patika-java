package com.allianz.erp.entity.concretes;

import com.allianz.erp.entity.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "bills")
@Data
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "bill_uuid"
        )
)
@AttributeOverride(
        name = "id",
        column = @Column(
                name = "bill_id"
        )
)
public class Bill extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "order_id", unique = true)
    private Order order;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;



}
