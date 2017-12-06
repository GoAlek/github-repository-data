package com.allegro.recruitment.github.repository;

import com.allegro.recruitment.github.repository.dto.GithubRepoData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class GithubRestRepository {

    private final String gitHubReposUri;
    private final RestTemplate githubRestTemplate;

    public GithubRestRepository(
            @Value("${github-repos-uri}") String gitHubReposUri,
            @Qualifier("githubRestTemplate") RestTemplate githubRestTemplate) {
        this.gitHubReposUri = gitHubReposUri;
        this.githubRestTemplate = githubRestTemplate;
    }

    public GithubRepoData getGithubRepositoryData(String owner, String repositoryName) {
        return githubRestTemplate.getForObject(gitHubReposUri, GithubRepoData.class, owner, repositoryName);
    }

    public RestTemplate getGithubRestTemplate() {
        return githubRestTemplate;
    }
}
