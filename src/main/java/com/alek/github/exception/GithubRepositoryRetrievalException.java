package com.alek.github.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
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
}
