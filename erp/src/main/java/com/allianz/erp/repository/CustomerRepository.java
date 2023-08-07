package com.allianz.erp.repository;

import com.allianz.erp.entity.concretes.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends BaseRepository<Customer>{

    Optional<Customer> findByUuid(UUID uuid);

}
