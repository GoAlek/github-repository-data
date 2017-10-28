package com.allegro.recruitment.controller;

import com.allegro.recruitment.controller.dto.RepositoryDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/repositories")
public class GithubRepositoryController {

    @GetMapping("/{owner}/{repository-name}")
    public RepositoryDetails getRepositoryDetails(
            @PathVariable String owner,
            @PathVariable("repository-name") String repositoryName) {
        return null;
    }
}
