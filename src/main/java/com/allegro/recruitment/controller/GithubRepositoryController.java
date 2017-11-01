package com.allegro.recruitment.controller;

import com.allegro.recruitment.controller.dto.GithubRepositoryDetails;
import com.allegro.recruitment.service.GithubRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repositories")
public class GithubRepositoryController {

    private GithubRepositoryService githubRepositoryService;

    @Autowired
    public GithubRepositoryController(GithubRepositoryService githubRepositoryService) {
        this.githubRepositoryService = githubRepositoryService;
    }

    @GetMapping("/{owner}/{repository-name}")
    public GithubRepositoryDetails getRepositoryDetails(
            @PathVariable String owner,
            @PathVariable("repository-name") String repositoryName) {
        return githubRepositoryService.getGithubRepositoryDetails(owner, repositoryName);
    }
}
