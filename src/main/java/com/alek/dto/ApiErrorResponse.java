package com.alek.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Getter
public class ApiErrorResponse {
    private HttpStatus status;
    private String timestamp;
    private String exception;
    private String message;
    private String info;
    private Map<String, String> requestParameters;

    private ApiErrorResponse() {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public ApiErrorResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiErrorResponse(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error has occurred.";
        this.exception = ex.getClass().getCanonicalName();
        this.info = ex.getLocalizedMessage();
    }

    public ApiErrorResponse(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.exception = ex.getClass().getCanonicalName();
        this.info = ex.getLocalizedMessage();
    }

    public ApiErrorResponse(HttpStatus status, String message, Throwable ex, Map<String, String> requestParameters) {
        this();
        this.status = status;
        this.message = message;
        this.exception = ex.getClass().getCanonicalName();
        this.info = ex.getLocalizedMessage();
        this.requestParameters = requestParameters;
    }
}
