package com.allegro.recruitment.dto;

import com.allegro.recruitment.github.controller.dto.RepositoryDetails;
import com.allegro.recruitment.github.repository.dto.GithubRepoData;
import org.springframework.stereotype.Component;

@Component
public class GithubRepositoryDetailsMapper {

    public RepositoryDetails map(GithubRepoData githubRepoData) {
        return RepositoryDetails.builder()
                .fullName(githubRepoData.getFull_name())
                .description(githubRepoData.getDescription())
                .createdAt(githubRepoData.getCreated_at())
                .cloneUrl(githubRepoData.getClone_url())
                .stars(githubRepoData.getStargazers_count())
                .build();
    }
}
