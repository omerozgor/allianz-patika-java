package com.allianz.erp.repository;

import com.allianz.erp.entity.concretes.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends BaseRepository<Product>{

    Optional<Product> findByUuid(UUID uuid);

}
