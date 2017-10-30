package com.allegro.recruitment.service;

import com.allegro.recruitment.controller.dto.GithubRepositoryDetails;
import com.allegro.recruitment.repository.GithubRestRepository;
import com.allegro.recruitment.repository.dto.GithubRepository;
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

    public GithubRepositoryDetails getRepositoryDetails(String owner, String repositoryName) {
        GithubRepository githubRepository = githubRestRepository.getGithubRepository(owner, repositoryName);
        return githubRepositoryDetailsMapper.map(githubRepository);
    }
}
