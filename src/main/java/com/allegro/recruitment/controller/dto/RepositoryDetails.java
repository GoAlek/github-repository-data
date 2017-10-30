package com.allegro.recruitment.controller.dto;

public class RepositoryDetails {

    private String fullName;
    private String description;
    private String cloneUrl;
    private Integer stars;
    private String createdAt;

    public RepositoryDetails(String fullName, String description, String cloneUrl, Integer stars, String createdAt) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.stars = stars;
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public Integer getStars() {
        return stars;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public static class RepositoryDetailsBuilder {
        private String fullName;
        private String description;
        private String cloneUrl;
        private Integer stars;
        private String createdAt;

        public RepositoryDetailsBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public RepositoryDetailsBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RepositoryDetailsBuilder cloneUrl(String cloneUrl) {
            this.cloneUrl = cloneUrl;
            return this;
        }

        public RepositoryDetailsBuilder stars(Integer stars) {
            this.stars = stars;
            return this;
        }

        public RepositoryDetailsBuilder createdAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RepositoryDetails build() {
            return new RepositoryDetails(fullName, description, cloneUrl, stars, createdAt);
        }
    }

}
