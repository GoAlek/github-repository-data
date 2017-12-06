package com.allegro.recruitment.github.repository.dto;

import com.allegro.recruitment.github.controller.dto.RepositoryDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GithubRepoData {
    @JsonProperty("full_name")
    private String fullName;

    private String description;

    @JsonProperty("clone_url")
    private String cloneUrl;

    @JsonProperty("stargazers_count")
    private Integer stargazersCount;

    @JsonProperty("created_at")
    private String createdAt;

    public static RepositoryDetails map(GithubRepoData githubRepoData) {
        return RepositoryDetails.builder()
                .fullName(githubRepoData.getFullName())
                .description(githubRepoData.getDescription())
                .createdAt(githubRepoData.getCreatedAt())
                .cloneUrl(githubRepoData.getCloneUrl())
                .stars(githubRepoData.getStargazersCount())
                .build();
    }
}
