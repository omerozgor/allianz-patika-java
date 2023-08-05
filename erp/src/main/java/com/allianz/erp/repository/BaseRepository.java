package com.allianz.erp.repository;

import com.allianz.erp.entity.abstracts.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T,Long> {

}
