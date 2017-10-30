package com.allegro.recruitment.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GithubRepositoryDetails {
    private String fullName;
    private String description;
    private String cloneUrl;
    private Integer stars;
    private String createdAt;
}
