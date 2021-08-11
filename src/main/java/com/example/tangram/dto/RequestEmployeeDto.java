package com.example.tangram.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Dto class for request
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RequestEmployeeDto {
    /**
     * teudat zeut of employee - unique field
     */
    @NotNull(message = "teudat zeut cannot be empty")
    private Long tz;
    /**
     * name of employee
     */
    @NotNull(message = "name cannot be empty")
    private String name;
    /**
     * last name of employee
     */
    @NotNull(message = "last name cannot be empty")
    private String lastName;
    /**
     * birthday of employee in format "YYYY-MM-DD"
     */
    @NotNull(message = "birthday cannot be empty")
    private String birthday;
    /**
     * date when employee start his work in format "YYYY-MM-DD"
     */
    @NotNull(message = "start work cannot be empty")
    private String startWork;
}
