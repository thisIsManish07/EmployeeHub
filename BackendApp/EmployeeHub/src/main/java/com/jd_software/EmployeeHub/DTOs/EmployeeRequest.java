package com.jd_software.EmployeeHub.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class EmployeeRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String department;

    @NotBlank
    private String status;

    @NotNull
    private LocalDate joinedOn;

    public EmployeeRequest() {}

    public EmployeeRequest(String alice, String hr, String active, LocalDate of) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getJoinedOn() {
        return joinedOn;
    }

    public void setJoinedOn(LocalDate joinedOn) {
        this.joinedOn = joinedOn;
    }
}
