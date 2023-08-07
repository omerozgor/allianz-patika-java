package com.allianz.erp.repository;

import com.allianz.erp.entity.concretes.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends BaseRepository<Order>{

    Optional<Order> findByUuid(UUID uuid);
}
