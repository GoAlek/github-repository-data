package com.allegro.recruitment.github.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class GithubRepositoryRetrievalException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String repositoryOwner;
    private final String repositoryName;

    public GithubRepositoryRetrievalException(
            String message,
            HttpStatus statusCode,
            String repositoryOwner,
            String repositoryName) {
        super(message);
        this.statusCode = statusCode;
        this.repositoryOwner = repositoryOwner;
        this.repositoryName = repositoryName;
    }

    public GithubRepositoryRetrievalException(
            String message,
            Throwable cause,
            HttpStatus statusCode,
            String repositoryOwner,
            String repositoryName) {
        super(message, cause);
        this.statusCode = statusCode;
        this.repositoryOwner = repositoryOwner;
        this.repositoryName = repositoryName;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getRepositoryOwner() {
        return repositoryOwner;
    }

    public String getRepositoryName() {
        return repositoryName;
    }
}
