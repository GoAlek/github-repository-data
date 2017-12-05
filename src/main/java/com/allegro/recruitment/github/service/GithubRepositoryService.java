package com.allegro.recruitment.github.service;

import com.allegro.recruitment.github.controller.dto.RepositoryDetails;
import com.allegro.recruitment.github.exception.GithubRepositoryNotFoundException;
import com.allegro.recruitment.github.repository.GithubRestRepository;
import com.allegro.recruitment.github.repository.dto.GithubRepoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class GithubRepositoryService {

    private GithubRestRepository githubRestRepository;

    @Autowired
    public GithubRepositoryService(GithubRestRepository githubRestRepository) {
        this.githubRestRepository = githubRestRepository;
    }

    public RepositoryDetails getGithubRepositoryDetails(String owner, String repositoryName) {
        GithubRepoData githubRepoData;
        try {
            githubRepoData = githubRestRepository.getGithubRepositoryData(owner, repositoryName);
        } catch (HttpClientErrorException ex) {
            throw new GithubRepositoryNotFoundException(ex.getStatusCode(), ex.getStatusText());
        }
        return GithubRepoData.map(githubRepoData);
    }
}
