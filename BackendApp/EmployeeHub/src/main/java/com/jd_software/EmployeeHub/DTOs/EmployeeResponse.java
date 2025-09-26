package com.jd_software.EmployeeHub.DTOs;

import java.time.LocalDate;
import java.util.UUID;

public class EmployeeResponse {
    private UUID id;
    private String name;
    private String department;
    private String status;
    private LocalDate joinedOn;

    public EmployeeResponse() {}

    public EmployeeResponse(UUID id, String name, String department, String status, LocalDate joinedOn) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.status = status;
        this.joinedOn = joinedOn;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
