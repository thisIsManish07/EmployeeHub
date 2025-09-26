package com.jd_software.EmployeeHub.Service;

import com.jd_software.EmployeeHub.DTOs.EmployeeRequest;
import com.jd_software.EmployeeHub.DTOs.EmployeeResponse;
import com.jd_software.EmployeeHub.DTOs.PagedResponse;
import com.jd_software.EmployeeHub.Entity.Employee;
import com.jd_software.EmployeeHub.Repository.EmployeeRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employee employee = new Employee(
                request.getName(),
                request.getDepartment(),
                request.getStatus(),
                request.getJoinedOn()
        );
        Employee saved = employeeRepository.save(employee);
        return mapToResponse(saved);
    }

    @Override
    public EmployeeResponse updateEmployee(UUID id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(request.getName());
        employee.setDepartment(request.getDepartment());
        employee.setStatus(request.getStatus());
        employee.setJoinedOn(request.getJoinedOn());

        return mapToResponse(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeResponse getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return mapToResponse(employee);
    }

    @Override
    public PagedResponse<EmployeeResponse> getAllEmployees(int page, int size, String sortBy, String direction, String search) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Employee> employeePage;

        if (search != null && !search.isEmpty()) {
            employeePage = employeeRepository.findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCaseOrStatusContainingIgnoreCase(
                    search, search, search, pageable
            );
        } else {
            employeePage = employeeRepository.findAll(pageable);
        }

        List<EmployeeResponse> responses = employeePage.getContent()
                .stream().map(this::mapToResponse).toList();

        return new PagedResponse<>(
                responses,
                employeePage.getNumber(),
                employeePage.getSize(),
                employeePage.getTotalElements(),
                employeePage.getTotalPages(),
                employeePage.isLast()
        );
    }


    private EmployeeResponse mapToResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getStatus(),
                employee.getJoinedOn()
        );
    }
}
