package com.hotelmanager.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class EmployeeDTO implements Serializable {
    private String id;
    @NotBlank(message = "Name is not null")
    @Pattern(regexp = "(^$|[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ])*$", message = "Name is not valid")
    private String name;
    @NotBlank(message = "Birthday is not null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthday;
    @NotBlank(message = "Email is not null")
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Phone number is not null")
    @Pattern(regexp = "(^$|[0-9]{10})*$",message = "Phone number must has 10 numbers")
    private String phoneNumber;
    private String gender;
    @NotBlank(message = "CMND is not null")
    private String CMND;
    @NotBlank(message = "Employee group is not null")
    private String employeeGroup;
    private String address;
    private String employeeCode;


    private Boolean deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getEmployeeGroup() {
        return employeeGroup;
    }

    public void setEmployeeGroup(String employeeGroup) {
        this.employeeGroup = employeeGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
