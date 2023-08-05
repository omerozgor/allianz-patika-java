package com.allianz.erp.entity.concretes;

import com.allianz.erp.entity.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "isKdvApplied")
    private boolean isKdvApplied;
    @Column(name = "priceWithoutKdv")
    private BigDecimal priceWithoutKdv;
    @Column(name = "stockAmount")
    private int stockAmount;
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

}
