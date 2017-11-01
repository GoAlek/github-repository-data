package com.allegro.recruitment.service;

import com.allegro.recruitment.controller.dto.GithubRepositoryDetails;
import com.allegro.recruitment.dto.GithubRepositoryDetailsMapper;
import com.allegro.recruitment.repository.GithubRestRepository;
import com.allegro.recruitment.repository.dto.GithubRepoData;
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

    @Mock
    private GithubRepositoryDetailsMapper githubRepositoryDetailsMapper;

    @InjectMocks
    private GithubRepositoryService githubRepositoryService;

    @Test
    public void getRepositoryDetails_ReturnRepositoryDetails_IfOwnerAndRepositoryAreValid() {
        final String testOwner = "testOwner", testRepositoryName = "testRepository";
        GithubRepoData githubRepoData = GithubRepoData.builder()
                .full_name("testRepository")
                .description("testDescription")
                .clone_url("https://github.com/test/test.git")
                .stargazers_count(10590)
                .created_at("2015-03-19T18:21:20Z")
                .build();

        when(githubRestRepository.getGithubRepositoryData(testOwner, testRepositoryName)).thenReturn(githubRepoData);
        when(githubRepositoryDetailsMapper.map(githubRepoData)).thenCallRealMethod();

        GithubRepositoryDetails result = githubRepositoryService
                .getGithubRepositoryDetails(testOwner, testRepositoryName);

        assertThat(result, hasProperty("fullName", equalTo(githubRepoData.getFull_name())));
        assertThat(result, hasProperty("description", equalTo(githubRepoData.getDescription())));
        assertThat(result, hasProperty("cloneUrl", equalTo(githubRepoData.getClone_url())));
        assertThat(result, hasProperty("stars", equalTo(githubRepoData.getStargazers_count())));
        assertThat(result, hasProperty("createdAt", equalTo(githubRepoData.getCreated_at())));
    }

}