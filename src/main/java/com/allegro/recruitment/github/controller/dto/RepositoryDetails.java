package com.allegro.recruitment.github.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RepositoryDetails {
    private String fullName;
    private String description;
    private String cloneUrl;
    private Integer stars;
    private String createdAt;
}
