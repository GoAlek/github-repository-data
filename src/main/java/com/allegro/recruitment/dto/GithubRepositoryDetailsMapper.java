package com.allegro.recruitment.dto;

import com.allegro.recruitment.controller.dto.GithubRepositoryDetails;
import com.allegro.recruitment.repository.dto.GithubRepoData;
import org.springframework.stereotype.Component;

@Component
public class GithubRepositoryDetailsMapper {

    public GithubRepositoryDetails map(GithubRepoData githubRepoData) {
        return GithubRepositoryDetails.builder()
                .fullName(githubRepoData.getFull_name())
                .description(githubRepoData.getDescription())
                .createdAt(githubRepoData.getCreated_at())
                .cloneUrl(githubRepoData.getClone_url())
                .stars(githubRepoData.getStargazers_count())
                .build();
    }
}
