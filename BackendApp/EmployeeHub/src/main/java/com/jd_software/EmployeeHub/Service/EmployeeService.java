package com.jd_software.EmployeeHub.Service;

import com.jd_software.EmployeeHub.DTOs.EmployeeRequest;
import com.jd_software.EmployeeHub.DTOs.EmployeeResponse;
import com.jd_software.EmployeeHub.DTOs.PagedResponse;

import java.util.UUID;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest request);
    EmployeeResponse updateEmployee(UUID id, EmployeeRequest request);
    void deleteEmployee(UUID id);
    EmployeeResponse getEmployeeById(UUID id);
    PagedResponse<EmployeeResponse> getAllEmployees(int page, int size, String sortBy, String direction, String search);}
