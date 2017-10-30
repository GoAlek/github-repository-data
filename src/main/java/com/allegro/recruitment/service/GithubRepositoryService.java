package com.allegro.recruitment.service;

import com.allegro.recruitment.controller.dto.RepositoryDetails;
import com.allegro.recruitment.repository.GithubRestRepository;
import com.allegro.recruitment.repository.dto.GithubRepository;
import com.allegro.recruitment.dto.RepositoryDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GithubRepositoryService {

    private GithubRestRepository githubRestRepository;
    private RepositoryDetailsMapper repositoryDetailsMapper;

    @Autowired
    public GithubRepositoryService(
            GithubRestRepository githubRestRepository,
            RepositoryDetailsMapper repositoryDetailsMapper) {
        this.githubRestRepository = githubRestRepository;
        this.repositoryDetailsMapper = repositoryDetailsMapper;
    }

    public RepositoryDetails getRepositoryDetails(String owner, String repositoryName) {
        GithubRepository githubRepository = githubRestRepository.getGithubRepository(owner, repositoryName);
        return repositoryDetailsMapper.map(githubRepository);
    }
}
