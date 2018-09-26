package com.alek.github.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RepositoryDetails {
    private final String fullName;
    private final String description;
    private final String cloneUrl;
    private final Integer stars;
    private final String createdAt;
}
