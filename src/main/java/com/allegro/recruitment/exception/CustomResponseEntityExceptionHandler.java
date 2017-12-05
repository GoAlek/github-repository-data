package com.allegro.recruitment.exception;

import com.allegro.recruitment.dto.ApiErrorResponse;
import com.allegro.recruitment.github.exception.GithubRepositoryNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GithubRepositoryNotFoundException.class)
    public final ResponseEntity<ApiErrorResponse> handleGithubRepositoryNotFoundExceptions(
            GithubRepositoryNotFoundException ex,
            WebRequest request) {
        final String errorMessage = "Repository was not found in Github. " +
                "Make sure that your request parameters are correctly set.";
        log.error(errorMessage.concat( " Requested uri path: " + request.getDescription(false)), ex);
        return new ResponseEntity<>(
                new ApiErrorResponse(ex.getStatusCode(), errorMessage, ex),
                ex.getStatusCode());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public final ResponseEntity<ApiErrorResponse> handleServerExceptions(
            HttpServerErrorException ex,
            WebRequest request) {
        final String errorMessage = "Unexpected error has occurred on the server side.";
        log.error(errorMessage.concat( " Requested uri path: " + request.getDescription(false)), ex);
        return new ResponseEntity<>(
                new ApiErrorResponse(ex.getStatusCode(), errorMessage, ex),
                ex.getStatusCode());
    }
}
