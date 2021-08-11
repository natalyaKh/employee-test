package com.example.tangram.exeptions;
/**
 * {@link RuntimeException} for situation when employee not found in DB
 */
public class NotUniqueEmployeeException extends RuntimeException{
    public NotUniqueEmployeeException(String message)
    {
        super(message);
    }
}
