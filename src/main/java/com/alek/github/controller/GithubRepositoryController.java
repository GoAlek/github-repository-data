package com.alek.github.controller;

import com.alek.github.controller.dto.RepositoryDetails;
import com.alek.github.service.GithubRepositoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repositories")
public class GithubRepositoryController {

    private final GithubRepositoryService githubRepositoryService;

    @Autowired
    public GithubRepositoryController(GithubRepositoryService githubRepositoryService) {
        this.githubRepositoryService = githubRepositoryService;
    }

    @GetMapping("/{owner}/{repository-name}")
    @ApiOperation(
            value = "Returns details of given Github repository.",
            notes = "The owner and the repository must exist in Github.",
            response = RepositoryDetails.class)
    public RepositoryDetails getRepositoryDetails(
            @PathVariable String owner,
            @PathVariable("repository-name") String repositoryName) {
        return githubRepositoryService.getGithubRepositoryDetails(owner, repositoryName);
    }
}
