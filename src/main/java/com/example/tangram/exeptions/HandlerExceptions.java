package com.example.tangram.exeptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler Exception class
 */
@RestControllerAdvice
public class HandlerExceptions {
    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerExceptions.class);

    /**
     * @return all global exceptions
     */
    //   all exceptions
    @ExceptionHandler(Exception.class)
    public ErrorDto exceptionHandler(Exception ex) {
        LOGGER.warn(ex.getMessage());
        return ErrorDto.builder()
            .date(LocalDateTime.now())
            .error(ex.getMessage())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .build();
    }

    /**
     * @return exception of validation
     */
    //    validExceptions
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleValidationException(MethodArgumentNotValidException e, WebRequest request) {
        LOGGER.warn(e.getMessage());
        Map<String, String> map = new HashMap<>();
        BindingResult result = e.getBindingResult();
        result.getAllErrors().forEach(error -> {
            String field = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
            String message = error.getDefaultMessage();
            map.put(field, message);
        });

        return ErrorDto.builder()
            .date(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error(map.toString())
            .build();
    }

    /**
     * @return {@link NotUniqueEmployeeException} exception
     */
    //    not unique object
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(NotUniqueEmployeeException.class)
    public ErrorDto handleNotUniqueUserException(NotUniqueEmployeeException ex) {
        LOGGER.warn(ex.getMessage());
        return ErrorDto.builder()
            .date(LocalDateTime.now())
            .error(ex.getMessage())
            .status(HttpStatus.CONFLICT.value())
            .build();
    }

    /**
     * @return {@link EmployeeNotFoundException} exception
     */
    //    user not found exception
    @ResponseStatus(HttpStatus.GONE)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ErrorDto handleNotUniqueUserException(EmployeeNotFoundException ex) {
        LOGGER.warn(ex.getMessage());
        return ErrorDto.builder()
            .date(LocalDateTime.now())
            .error(ex.getMessage())
            .status(HttpStatus.GONE.value())
            .build();
    }
}

