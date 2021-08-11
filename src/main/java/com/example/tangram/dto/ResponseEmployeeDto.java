package com.example.tangram.dto;

import lombok.*;

import java.time.LocalDate;

/**
 * Dto class for response
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ResponseEmployeeDto {
    /**
     * teudat zeut of employee
     */
    private Long tz;
    /**
     * name of employee
     */
    private String name;
    /**
     * last name of employee
     */
    private String lastName;
    /**
     * birthday of employee in format "YYYY-MM-DD"
     */
    private LocalDate birthday;
    /**
     * work experience of employee in this company. In format "YYYY-MM-DD"
     */
    private Long workExperience;
}
