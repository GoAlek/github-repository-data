package com.allegro.recruitment.dto;

import com.allegro.recruitment.controller.dto.GithubRepositoryDetails;
import com.allegro.recruitment.repository.dto.GithubRepository;
import org.springframework.stereotype.Component;

@Component
public class GithubRepositoryDetailsMapper {

    public GithubRepositoryDetails map(GithubRepository githubRepository) {
        return GithubRepositoryDetails.builder()
                .fullName(githubRepository.getFull_name())
                .description(githubRepository.getDescription())
                .createdAt(githubRepository.getCreated_at())
                .cloneUrl(githubRepository.getClone_url())
                .stars(githubRepository.getStargazers_count())
                .build();
    }
}
