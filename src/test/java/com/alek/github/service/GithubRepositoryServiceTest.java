package com.alek.github.service;

import com.alek.github.controller.dto.RepositoryDetails;
import com.alek.github.repository.GithubRestRepository;
import com.alek.github.repository.dto.GithubRepoData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GithubRepositoryServiceTest {

    @Mock
    private GithubRestRepository githubRestRepository;

    @InjectMocks
    private GithubRepositoryService githubRepositoryService;

    @Test
    public void getRepositoryDetails_ReturnRepositoryDetails_IfOwnerAndRepositoryAreValid() {
        final String testOwner = "testOwner", testRepositoryName = "testRepository";
        GithubRepoData githubRepoData = GithubRepoData.builder()
                .fullName("testRepository")
                .description("testDescription")
                .cloneUrl("https://github.com/test/test.git")
                .stargazersCount(10590)
                .createdAt("2015-03-19T18:21:20Z")
                .build();

        when(githubRestRepository.getGithubRepositoryData(testOwner, testRepositoryName)).thenReturn(githubRepoData);

        RepositoryDetails result = githubRepositoryService
                .getGithubRepositoryDetails(testOwner, testRepositoryName);

        assertThat(result, hasProperty("fullName", equalTo(githubRepoData.getFullName())));
        assertThat(result, hasProperty("description", equalTo(githubRepoData.getDescription())));
        assertThat(result, hasProperty("cloneUrl", equalTo(githubRepoData.getCloneUrl())));
        assertThat(result, hasProperty("stars", equalTo(githubRepoData.getStargazersCount())));
        assertThat(result, hasProperty("createdAt", equalTo(githubRepoData.getCreatedAt())));
    }

}