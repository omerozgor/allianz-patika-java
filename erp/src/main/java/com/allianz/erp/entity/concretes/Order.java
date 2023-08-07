package com.allianz.erp.entity.concretes;

import com.allianz.erp.entity.abstracts.BaseEntity;
import com.allianz.erp.model.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "order_uuid"
        )
)
@AttributeOverride(
        name = "id",
        column = @Column(
                name = "order_id"
        )
)
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
    @Column(name = "order_status")
    private OrderStatusEnum orderStatus = OrderStatusEnum.WAITING_FOR_CONFIRMATION;

}
