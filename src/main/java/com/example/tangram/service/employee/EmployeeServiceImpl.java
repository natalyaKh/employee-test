package com.example.tangram.service.employee;

import com.example.tangram.constants.Constants;
import com.example.tangram.dto.RequestEmployeeDto;
import com.example.tangram.dto.ResponseEmployeeDto;
import com.example.tangram.enums.Status;
import com.example.tangram.model.Employee;
import com.example.tangram.repo.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * class implements of {@link EmployeeService}
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    EmployeeRepository repo;

    /**
     * method that create and save employee to DB
     * @param employeeDto {@link RequestEmployeeDto}
     * @return {@link Status} is CREATED
     */
    @Override
    public Status saveEmployee(RequestEmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setBirthday(LocalDate.parse(employeeDto.getBirthday()));
        employee.setStartWork(LocalDate.parse(employeeDto.getStartWork()));
        Employee restoredEmployee = repo.save(employee);

        LOGGER.info(Constants.EMPLOYEE_WITH_TZ + restoredEmployee.getTz() + Constants.SAVED);

        ResponseEmployeeDto responseEmployeeDto = modelMapper.map(restoredEmployee, ResponseEmployeeDto.class);
        responseEmployeeDto.setWorkExperience(getExperience(restoredEmployee.getStartWork()));
        return Status.CREATED;
    }

    /**
     * @return list of {@link ResponseEmployeeDto}
     */
    @Override
    public List<ResponseEmployeeDto> getAllEmployees() {
        List<Employee> employees = repo.findAll();
        if (employees.size() == 0) return new ArrayList<>();
        return employees.stream().map(this::getResponseEmployeeFromEmployeeEntity).collect(Collectors.toList());
    }

    /**
     * method that delete employee from DB
     * @param employee {@link Employee}
     * @return {@link Status} is DELETED
     */
    @Override
    public Status deleteEmployeeById(Employee employee) {
        repo.delete(employee);
        LOGGER.info(Constants.EMPLOYEE_WITH_TZ + employee.getTz() + Constants.DELETED);
        return Status.DELETED;
    }
    private ResponseEmployeeDto getResponseEmployeeFromEmployeeEntity(Employee employee) {
        ResponseEmployeeDto responseEmployeeDto = modelMapper.map(employee, ResponseEmployeeDto.class);
        responseEmployeeDto.setWorkExperience(getExperience(employee.getStartWork()));
        return responseEmployeeDto;
    }



    private Long getExperience(LocalDate startWork) {
        return ChronoUnit.YEARS.between(startWork, LocalDate.now());
    }

}
