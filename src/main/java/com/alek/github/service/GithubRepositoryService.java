package com.alek.github.service;

import com.alek.github.controller.dto.RepositoryDetails;
import com.alek.github.exception.GithubRepositoryRetrievalException;
import com.alek.github.repository.GithubRestRepository;
import com.alek.github.repository.dto.GithubRepoData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class GithubRepositoryService {

    private final GithubRestRepository githubRestRepository;

    public GithubRepositoryService(GithubRestRepository githubRestRepository) {
        this.githubRestRepository = githubRestRepository;
    }

    public RepositoryDetails getGithubRepositoryDetails(String owner, String repositoryName) {
        GithubRepoData githubRepoData;
        try {
            githubRepoData = githubRestRepository.getGithubRepositoryData(owner, repositoryName);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new GithubRepositoryRetrievalException(
                    ex.getStatusText(),
                    ex,
                    ex.getStatusCode(),
                    owner,
                    repositoryName);
        }
        return GithubRepoData.map(githubRepoData);
    }
}
