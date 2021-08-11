package com.example.tangram.service.employee;

import com.example.tangram.dto.RequestEmployeeDto;
import com.example.tangram.dto.ResponseEmployeeDto;
import com.example.tangram.enums.Status;
import com.example.tangram.model.Employee;

import java.util.List;

/**
 * Service for working with employee
 */
public interface EmployeeService {

    List<ResponseEmployeeDto> getAllEmployees();

    Status saveEmployee(RequestEmployeeDto employee);

    Status deleteEmployeeById(Employee employee);
}
