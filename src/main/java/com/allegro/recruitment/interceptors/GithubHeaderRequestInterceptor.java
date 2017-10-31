package com.allegro.recruitment.interceptors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubHeaderRequestInterceptor implements ClientHttpRequestInterceptor {

    private final String githubAcceptHeaderValue;
    private final String githubAuthorizationHeaderValue;

    public GithubHeaderRequestInterceptor(
            @Value("${github-accept-header}") String githubAcceptHeaderValue,
            @Value("${github-authorization-header}") String githubAuthorizationHeaderValue) {
        this.githubAcceptHeaderValue = githubAcceptHeaderValue;
        this.githubAuthorizationHeaderValue = githubAuthorizationHeaderValue;
    }

    @Override
    public ClientHttpResponse intercept(
            HttpRequest httpRequest,
            byte[] bytes,
            ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        httpRequest.getHeaders().set(HttpHeaders.ACCEPT, githubAcceptHeaderValue);
        httpRequest.getHeaders().set(HttpHeaders.AUTHORIZATION, githubAuthorizationHeaderValue);
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
