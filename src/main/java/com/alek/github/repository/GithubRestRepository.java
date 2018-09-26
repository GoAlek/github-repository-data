package com.alek.github.repository;

import com.alek.github.repository.dto.GithubRepoData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Repository
public class GithubRestRepository {
    private final String githubReposUri;
    private final RestTemplate githubRestTemplate;

    public GithubRestRepository(
            @Value("${github-repos-uri}") String githubReposUri,
            @Qualifier("githubRestTemplate") RestTemplate githubRestTemplate) {
        this.githubReposUri = githubReposUri;
        this.githubRestTemplate = githubRestTemplate;
    }

    public GithubRepoData getGithubRepositoryData(String owner, String repositoryName)
            throws HttpClientErrorException, HttpServerErrorException
    {
        return githubRestTemplate.getForObject(githubReposUri, GithubRepoData.class, owner, repositoryName);
    }
}
