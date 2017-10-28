package com.allegro.recruitment.controller;

import com.allegro.recruitment.controller.dto.RepositoryDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController("/repositories")
public class GithubRepositoryController {

    public RepositoryDetails getRepositoryDetails() {
        return null;
    }
}
