package com.allegro.recruitment.service;

import com.allegro.recruitment.controller.dto.RepositoryDetails;
import com.allegro.recruitment.repository.GithubRestRepository;
import com.allegro.recruitment.repository.dto.GithubRepoData;
import com.allegro.recruitment.dto.GithubRepositoryDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GithubRepositoryService {

    private GithubRestRepository githubRestRepository;
    private GithubRepositoryDetailsMapper githubRepositoryDetailsMapper;

    @Autowired
    public GithubRepositoryService(
            GithubRestRepository githubRestRepository,
            GithubRepositoryDetailsMapper githubRepositoryDetailsMapper) {
        this.githubRestRepository = githubRestRepository;
        this.githubRepositoryDetailsMapper = githubRepositoryDetailsMapper;
    }

    public RepositoryDetails getGithubRepositoryDetails(String owner, String repositoryName) {
        GithubRepoData githubRepoData = githubRestRepository.getGithubRepositoryData(owner, repositoryName);
        return githubRepositoryDetailsMapper.map(githubRepoData);
    }
}
