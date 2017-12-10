package com.allegro.recruitment.exception;

import com.allegro.recruitment.dto.ApiErrorResponse;
import com.allegro.recruitment.github.exception.GithubRepositoryRetrievalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GithubRepositoryRetrievalException.class)
    public final ResponseEntity<ApiErrorResponse> handleGithubRepositoryNotFoundExceptions(
            GithubRepositoryRetrievalException ex,
            WebRequest request) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append("Client error has occurred while retrieving repository details from Github.");
        errorMessageBuilder.append(" Repository owner: ").append(ex.getOwner());
        errorMessageBuilder.append(" Repository name: ").append(ex.getRepositoryName()).append(". ");
        errorMessageBuilder.append("Make sure that your request parameters are correctly set.");
        final String errorMessage = errorMessageBuilder.toString();
        errorMessageBuilder.append(" Requested uri path: ").append(request.getDescription(false));
        log.error(errorMessageBuilder.toString(), ex);
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
