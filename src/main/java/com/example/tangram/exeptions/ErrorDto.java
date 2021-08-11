package com.example.tangram.exeptions;

import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO class for getting exceptions
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorDto {
    /**
     * field with description of exception
     */
    String error;
    /**
     * field shows time of exception
     */
    LocalDateTime date;

    /**
     * field shows {@link org.springframework.http.HttpStatus} of exception
     */
    int status;
}
