package com.allegro.recruitment.github.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class GithubRepositoryNotFoundException extends HttpClientErrorException {

    public GithubRepositoryNotFoundException(HttpStatus statusCode) {
        super(statusCode);
    }

    public GithubRepositoryNotFoundException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

}
