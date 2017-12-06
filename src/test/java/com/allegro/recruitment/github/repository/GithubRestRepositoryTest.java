package com.allegro.recruitment.github.repository;

import com.allegro.recruitment.github.repository.dto.GithubRepoData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GithubRestRepositoryTest {

    private final String githubReposUri = "test-uri";

    @Mock
    private RestTemplate githubRestTemplate;

    private GithubRestRepository githubRestRepository;

    @Before
    public void setUp() {
        githubRestRepository = new GithubRestRepository(githubReposUri, githubRestTemplate);
    }

    @Test
    public void getGithubRepositoryData_ReturnRepositoryData_IfOwnerAndRepositoryAreValid() {
        final String testOwner = "testOwner", testRepositoryName = "testRepository";
        GithubRepoData githubRepoData = GithubRepoData.builder()
                .full_name("testRepository")
                .description("testDescription")
                .clone_url("https://github.com/test/test.git")
                .stargazers_count(10590)
                .created_at("2015-03-19T18:21:20Z")
                .build();

        when(githubRestTemplate.getForObject(githubReposUri, GithubRepoData.class, testOwner, testRepositoryName))
                .thenReturn(githubRepoData);

        GithubRepoData result = githubRestRepository.getGithubRepositoryData(testOwner, testRepositoryName);

        assertEquals(githubRepoData, result);
    }
}