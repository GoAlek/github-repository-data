package com.allegro.recruitment.github.exception;

import com.allegro.recruitment.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GithubResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GithubRepositoryRetrievalException.class)
    public final ResponseEntity<ApiErrorResponse> handleGithubRepositoryRetrievalExceptions(
            GithubRepositoryRetrievalException ex,
            WebRequest request) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append("Unexpected error has occurred while retrieving repository details from Github.");
        errorMessageBuilder.append(" Make sure that your request parameters are correctly set.");
        final String errorMessage = errorMessageBuilder.toString();

        errorMessageBuilder.append(" Requested uri path: ").append(request.getDescription(false));
        log.error(errorMessageBuilder.toString(), ex);

        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put("repositoryOwner", ex.getRepositoryOwner());
        requestParameters.put("repositoryName", ex.getRepositoryName());

        return new ResponseEntity<>(
                new ApiErrorResponse(ex.getStatusCode(), errorMessage, ex, requestParameters),
                ex.getStatusCode());
    }
}
