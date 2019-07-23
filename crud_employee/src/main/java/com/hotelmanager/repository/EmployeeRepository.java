package com.hotelmanager.repository;

import com.hotelmanager.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
    Page<EmployeeEntity> findAllByDeletedIsFalse(Pageable pageable);
    Page<EmployeeEntity> findAllByDeletedIsFalseAndNameContaining(String name, Pageable pageable);
}
