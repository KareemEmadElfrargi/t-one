package com.kareem.tone.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String error;
    private int status;
    private LocalDateTime timestamp;
}
