package com.allegro.recruitment.github.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GithubRestTemplateConfig {

    @Bean(name = "githubRestTemplate")
    public RestTemplate githubRestTemplate(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${github-root-uri}") String gitHubRootUri,
            @Qualifier("githubHeaderRequestInterceptor") ClientHttpRequestInterceptor clientHttpRequestInterceptor) {
            return restTemplateBuilder
                    .rootUri(gitHubRootUri)
                    .interceptors(clientHttpRequestInterceptor)
                    .build();
    }

}
