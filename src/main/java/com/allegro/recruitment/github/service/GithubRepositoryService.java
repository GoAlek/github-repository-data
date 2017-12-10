package com.allegro.recruitment.github.service;

import com.allegro.recruitment.github.controller.dto.RepositoryDetails;
import com.allegro.recruitment.github.exception.GithubRepositoryRetrievalException;
import com.allegro.recruitment.github.repository.GithubRestRepository;
import com.allegro.recruitment.github.repository.dto.GithubRepoData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
        } catch (HttpClientErrorException ex) {
            throw new GithubRepositoryRetrievalException(ex.getStatusCode(), ex.getStatusText(), owner, repositoryName);
        }
        return GithubRepoData.map(githubRepoData);
    }
}
