package com.alek.github.repository.dto;

import com.alek.github.controller.dto.RepositoryDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GithubRepoData {
    @JsonProperty("full_name")
    private final String fullName;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("clone_url")
    private final String cloneUrl;

    @JsonProperty("stargazers_count")
    private final Integer stargazersCount;

    @JsonProperty("created_at")
    private final String createdAt;

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
