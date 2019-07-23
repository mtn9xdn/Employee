package com.hotelmanager.service;

import com.hotelmanager.dto.EmployeeDTO;
import com.hotelmanager.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Page<EmployeeEntity> findAllByDeletedIsFalse(Pageable pageable);
    Page<EmployeeEntity> findAllByDeletedIsFalseAndNameContaining(String name, Pageable pageable);


    void create(EmployeeDTO employeeDTO);
    void update(EmployeeDTO employeeDTO);
    void delete(String id);
    EmployeeDTO findById(String id);
}
