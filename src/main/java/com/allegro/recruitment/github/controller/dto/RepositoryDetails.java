package com.allegro.recruitment.github.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RepositoryDetails {
    private String fullName;
    private String description;
    private String cloneUrl;
    private Integer stars;
    private String createdAt;
}
