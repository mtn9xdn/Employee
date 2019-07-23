package com.hotelmanager.service.impl;

import com.hotelmanager.constants.AppConsts;
import com.hotelmanager.dto.EmployeeDTO;
import com.hotelmanager.entity.EmployeeEntity;
import com.hotelmanager.repository.EmployeeRepository;
import com.hotelmanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<EmployeeEntity> findAllByDeletedIsFalse(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 10, Sort.by("name").ascending());
        return employeeRepository.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public Page<EmployeeEntity> findAllByDeletedIsFalseAndNameContaining(String name, Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 10, Sort.by("name").ascending());
        return employeeRepository.findAllByDeletedIsFalseAndNameContaining(name, pageable);
    }

    @Override
    public void create(EmployeeDTO employeeDTO) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(AppConsts.STRING_TO_DATE_FORMAT);
        LocalDate birthday = LocalDate.parse(employeeDTO.getBirthday());

        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setEmployeeGroup(employeeEntity.getEmployeeGroup());
        employeeEntity.setName(employeeEntity.getName());
        employeeEntity.setBirthday(birthday);
        employeeEntity.setGender(employeeEntity.getGender());
        employeeEntity.setPhoneNumber(employeeEntity.getPhoneNumber());
        employeeEntity.setCMND(employeeEntity.getCMND());
        employeeEntity.setAddress(employeeEntity.getAddress());
        employeeEntity.setEmployeeCode(employeeEntity.getEmployeeCode());
        employeeEntity.setEmail(employeeEntity.getEmail());
        employeeEntity.setDeleted(Boolean.FALSE);

        employeeRepository.save(employeeEntity);

    }
    @Override
    public EmployeeDTO findById(String id) {

        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);

        if(employeeEntity != null){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(AppConsts.STRING_TO_DATE_FORMAT);
            String birthday = employeeEntity.getBirthday().format(dateTimeFormatter);

            EmployeeDTO employeeDTO = new EmployeeDTO();

            employeeDTO.setEmployeeGroup(employeeDTO.getEmployeeGroup());
            employeeDTO.setName(employeeDTO.getName());
            employeeDTO.setBirthday(birthday);
            employeeDTO.setGender(employeeDTO.getGender());
            employeeDTO.setPhoneNumber(employeeDTO.getPhoneNumber());
            employeeDTO.setCMND(employeeDTO.getCMND());
            employeeDTO.setAddress(employeeDTO.getAddress());
            employeeDTO.setEmployeeCode(employeeDTO.getEmployeeCode());
            employeeDTO.setEmail(employeeDTO.getEmail());
            employeeDTO.setDeleted(Boolean.FALSE);

            return employeeDTO;
        }
        return null;
    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(AppConsts.STRING_TO_DATE_FORMAT);
        LocalDate birthday = LocalDate.parse(employeeDTO.getBirthday());

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeDTO.getId()).orElse(null);

        employeeEntity.setEmployeeGroup(employeeEntity.getEmployeeGroup());
        employeeEntity.setName(employeeEntity.getName());
        employeeEntity.setBirthday(birthday);
        employeeEntity.setGender(employeeEntity.getGender());
        employeeEntity.setPhoneNumber(employeeEntity.getPhoneNumber());
        employeeEntity.setCMND(employeeEntity.getCMND());
        employeeEntity.setAddress(employeeEntity.getAddress());
        employeeEntity.setEmployeeCode(employeeEntity.getEmployeeCode());
        employeeEntity.setEmail(employeeEntity.getEmail());
        employeeEntity.setDeleted(Boolean.FALSE);

        employeeRepository.save(employeeEntity);
    }

    @Override
    public void delete(String id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        employeeEntity.setDeleted(true);
        employeeRepository.save(employeeEntity);

    }



}
