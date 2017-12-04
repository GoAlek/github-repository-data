package com.allegro.recruitment.github.service;

import com.allegro.recruitment.github.controller.dto.RepositoryDetails;
import com.allegro.recruitment.github.repository.GithubRestRepository;
import com.allegro.recruitment.github.repository.dto.GithubRepoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GithubRepositoryService {

    private GithubRestRepository githubRestRepository;

    @Autowired
    public GithubRepositoryService(GithubRestRepository githubRestRepository) {
        this.githubRestRepository = githubRestRepository;
    }

    public RepositoryDetails getGithubRepositoryDetails(String owner, String repositoryName) {
        GithubRepoData githubRepoData = githubRestRepository.getGithubRepositoryData(owner, repositoryName);
        return GithubRepoData.map(githubRepoData);
    }
}
