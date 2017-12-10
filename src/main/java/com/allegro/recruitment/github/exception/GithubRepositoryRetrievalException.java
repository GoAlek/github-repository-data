package com.allegro.recruitment.github.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class GithubRepositoryRetrievalException extends HttpClientErrorException {
    private final String owner;
    private final String repositoryName;

    public GithubRepositoryRetrievalException(
            HttpStatus statusCode,
            String owner,
            String repositoryName) {
        super(statusCode);
        this.owner = owner;
        this.repositoryName = repositoryName;
    }

    public GithubRepositoryRetrievalException(
            HttpStatus statusCode,
            String statusText,
            String owner,
            String repositoryName) {
        super(statusCode, statusText);
        this.owner = owner;
        this.repositoryName = repositoryName;
    }

    public String getOwner() {
        return owner;
    }

    public String getRepositoryName() {
        return repositoryName;
    }
}
