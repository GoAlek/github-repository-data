package com.allegro.recruitment.github.repository;

import com.allegro.recruitment.github.repository.dto.GithubRepoData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class GithubRestRepository {

    private final RestTemplate githubRestTemplate;

    public GithubRestRepository(@Qualifier("githubRestTemplate") RestTemplate githubRestTemplate) {
        this.githubRestTemplate = githubRestTemplate;
    }

    public GithubRepoData getGithubRepositoryData(String owner, String repositoryName) {
        return githubRestTemplate.getForObject("/repos/{owner}/{repo-name}", GithubRepoData.class, owner, repositoryName);
    }

    public RestTemplate getGithubRestTemplate() {
        return githubRestTemplate;
    }
}
