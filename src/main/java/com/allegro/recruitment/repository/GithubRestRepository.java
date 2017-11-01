package com.allegro.recruitment.repository;

import com.allegro.recruitment.repository.dto.GithubRepoData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class GithubRestRepository {

    private final RestTemplate restTemplate;

    public GithubRestRepository(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${github-root-uri}") String gitHubRootUri,
            @Qualifier("githubHeaderRequestInterceptor") ClientHttpRequestInterceptor clientHttpRequestInterceptor) {
        this.restTemplate = restTemplateBuilder
                .rootUri(gitHubRootUri)
                .interceptors(clientHttpRequestInterceptor)
                .build();
    }

    public GithubRepoData getGithubRepositoryData(String owner, String repositoryName) {
        return restTemplate.getForObject("/repos/{owner}/{repo-name}", GithubRepoData.class, owner, repositoryName);
    }


}
