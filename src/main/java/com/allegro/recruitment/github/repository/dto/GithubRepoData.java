package com.allegro.recruitment.github.repository.dto;

import com.allegro.recruitment.github.controller.dto.RepositoryDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GithubRepoData {
    private String full_name;
    private String description;
    private String clone_url;
    private Integer stargazers_count;
    private String created_at;

    public static RepositoryDetails map(GithubRepoData githubRepoData) {
        return RepositoryDetails.builder()
                .fullName(githubRepoData.getFull_name())
                .description(githubRepoData.getDescription())
                .createdAt(githubRepoData.getCreated_at())
                .cloneUrl(githubRepoData.getClone_url())
                .stars(githubRepoData.getStargazers_count())
                .build();
    }
}
