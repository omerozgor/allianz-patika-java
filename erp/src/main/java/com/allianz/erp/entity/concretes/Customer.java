package com.allianz.erp.entity.concretes;

import com.allianz.erp.entity.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "customer_uuid"
        )
)
@AttributeOverride(
        name = "id",
        column = @Column(
                name = "customer_id"
        )
)
public class Customer extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "postalCode")
    private String postalCode;
    @Column(name = "country")
    private String country;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

}
