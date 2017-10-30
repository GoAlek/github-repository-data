package com.allegro.recruitment.repository;

import com.allegro.recruitment.repository.dto.GithubRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@Repository
public class GithubRestRepository {

    private final String gitHubApiUrl = "https://api.github.com/repos/{0}/{1}";

    private RestTemplate restTemplate;

    public GithubRestRepository() {
        this.restTemplate = new RestTemplate();
    }

    public GithubRepository getGithubRepository(String owner, String repositoryName) {
        final String requestUrl = MessageFormat.format(gitHubApiUrl, owner, repositoryName);
        return restTemplate.getForObject(requestUrl, GithubRepository.class);
    }


}
