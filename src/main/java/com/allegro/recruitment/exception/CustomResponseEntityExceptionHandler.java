package com.allegro.recruitment.exception;

import com.allegro.recruitment.dto.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<ApiErrorResponse> handleClientExceptions(HttpClientErrorException ex, WebRequest request) {
        final String error = "Client error has occurred. Make sure that you request parameters are correctly set.";
        return new ResponseEntity<>(
                new ApiErrorResponse(ex.getStatusCode(), error, ex),
                ex.getStatusCode());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public final ResponseEntity<ApiErrorResponse> handleServerExceptions(HttpServerErrorException ex, WebRequest request) {
        final String error = "Unexpected error has occurred on the server side.";
        return new ResponseEntity<>(
                new ApiErrorResponse(ex.getStatusCode(), error, ex),
                ex.getStatusCode());
    }
}
