package com.example.tangram.exeptions;

/**
 * {@link RuntimeException} for situation when employee not exists in DB
 */
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message)
    {
        super(message);
    }
}
