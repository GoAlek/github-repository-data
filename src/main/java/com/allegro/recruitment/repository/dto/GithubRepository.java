package com.allegro.recruitment.repository.dto;

import lombok.Data;

@Data
public class GithubRepository {
    private String full_name;
    private String description;
    private String clone_url;
    private Integer stargazers_count;
    private String created_at;
}
