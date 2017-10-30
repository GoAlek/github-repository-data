package com.allegro.recruitment.repository;

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
            RestTemplateBuilder restTemplateBuilder,
            @Value("${github-root-uri}") String gitHubRootUri) {
        this.restTemplate = restTemplateBuilder.rootUri(gitHubRootUri).build();
    }

    public GithubRepository getGithubRepository(String owner, String repositoryName) {
        return restTemplate.getForObject("/repos/{owner}/{repo}", GithubRepository.class, owner, repositoryName);
    }


}
