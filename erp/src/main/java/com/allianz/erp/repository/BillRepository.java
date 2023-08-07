package com.allianz.erp.repository;

import com.allianz.erp.entity.concretes.Bill;
import com.allianz.erp.entity.concretes.Customer;

import java.util.Optional;
import java.util.UUID;

public interface BillRepository extends BaseRepository<Bill> {

    Optional<Bill> findByUuid(UUID uuid);
}
