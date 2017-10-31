package com.allegro.recruitment.repository;

import com.allegro.recruitment.interceptors.GithubHeaderRequestInterceptor;
import com.allegro.recruitment.repository.dto.GithubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class GithubRestRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public GithubRestRepository(
            @Value("${github-root-uri}") String gitHubRootUri,
            RestTemplateBuilder restTemplateBuilder,
            GithubHeaderRequestInterceptor githubHeaderRequestInterceptor) {
        this.restTemplate = restTemplateBuilder
                .rootUri(gitHubRootUri)
                .interceptors(githubHeaderRequestInterceptor)
                .build();
    }

    public GithubRepository getGithubRepository(String owner, String repositoryName) {
        return restTemplate.getForObject("/repos/{owner}/{repo}", GithubRepository.class, owner, repositoryName);
    }


}
