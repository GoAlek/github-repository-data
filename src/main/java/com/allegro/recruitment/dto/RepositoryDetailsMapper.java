package com.allegro.recruitment.dto;

import com.allegro.recruitment.controller.dto.RepositoryDetails;
import com.allegro.recruitment.repository.dto.GithubRepository;
import org.springframework.stereotype.Component;

@Component
public class RepositoryDetailsMapper {

    public RepositoryDetails map(GithubRepository githubRepository) {
        return new RepositoryDetails.RepositoryDetailsBuilder()
                .fullName(githubRepository.getFull_name())
                .description(githubRepository.getDescription())
                .createdAt(githubRepository.getCreated_at())
                .cloneUrl(githubRepository.getClone_url())
                .stars(githubRepository.getStargazers_count())
                .build();
    }
}
