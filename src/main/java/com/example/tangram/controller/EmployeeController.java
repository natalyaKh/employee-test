package com.example.tangram.controller;

import com.example.tangram.constants.Constants;
import com.example.tangram.dto.RequestEmployeeDto;
import com.example.tangram.dto.ResponseEmployeeDto;
import com.example.tangram.enums.Status;
import com.example.tangram.exeptions.NotUniqueEmployeeException;
import com.example.tangram.exeptions.EmployeeNotFoundException;
import com.example.tangram.model.Employee;
import com.example.tangram.service.employee.EmployeeService;
import com.example.tangram.service.validator.ValidateEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class {@link RestController}
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ValidateEmployeeService validaService;

    /**
     * @throws {@link NotUniqueEmployeeException} if employee with this tz exists in DB
     * @param employeeDto {@link RequestEmployeeDto}
     * @return {@link HttpStatus} is OK
     */
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity addEmployee(@RequestBody @Valid RequestEmployeeDto employeeDto) {
        Employee employee =  validaService.checkEmployeeByTz(employeeDto.getTz());
        if(employee != null){
            throw new NotUniqueEmployeeException(Constants.EMPLOYEE_WITH_TZ + employeeDto.getTz()
             + Constants.EXISTS);
        }
        Status rez = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * @return list of {@link ResponseEmployeeDto} employees
     */
    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public ResponseEntity  getAllEmployees() {
        List<ResponseEmployeeDto> rez = employeeService.getAllEmployees();
        return new ResponseEntity(rez, HttpStatus.OK);
    }

    /**
     * @throws {@link EmployeeNotFoundException} if employee with this tz not exists in DB
     * @param tz teudat zeut of employee
     * @return {@link HttpStatus} is OK
     */
    @RequestMapping(value = "/employee/{tz}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployeeByTZ(@PathVariable("tz") long tz) {
        Employee employee = validaService.checkEmployeeByTz(tz);
        if(employee == null){
            throw new EmployeeNotFoundException(Constants.EMPLOYEE_WITH_TZ + tz + Constants.NOT_FOUND);
        }
        Status rez = employeeService.deleteEmployeeById(employee);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @return {@link LocalDateTime} time of server
     */
    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public LocalDateTime getServerTime(){
        return LocalDateTime.now();
    }

}
