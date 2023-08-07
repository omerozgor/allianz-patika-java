package com.allianz.erp.repository;

import com.allianz.erp.entity.concretes.OrderDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderDetailRepository extends BaseRepository<OrderDetail>{

    List<OrderDetail> findAllByOrderId(int orderId);

    @Transactional
    void deleteAllByOrderId(int orderId);

}
