package com.allianz.erp.entity.concretes;


import com.allianz.erp.entity.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_details")
@Data
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "order_detail_uuid"
        )
)
@AttributeOverride(
        name = "id",
        column = @Column(
                name = "order_detail_id"
        )
)
public class OrderDetail extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
}
