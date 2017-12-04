package com.allegro.recruitment.github.controller;

import com.allegro.recruitment.github.controller.dto.RepositoryDetails;
import com.allegro.recruitment.github.service.GithubRepositoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GithubRepositoryControllerTest {

    @Mock
    private GithubRepositoryService githubRepositoryService;

    @InjectMocks
    private GithubRepositoryController githubRepositoryController;

    @Test
    public void getRepositoryDetails_ReturnProperResponse_IfOwnerAndRepositoryAreValid() {
        final String testOwner = "testOwner", testRepositoryName = "testRepository";
        RepositoryDetails repositoryDetails = RepositoryDetails.builder()
                .fullName("testRepository")
                .description("testDescription")
                .cloneUrl("https://github.com/test/test.git")
                .stars(10590)
                .createdAt("2015-03-19T18:21:20Z")
                .build();

        when(githubRepositoryService
                .getGithubRepositoryDetails(testOwner, testRepositoryName))
                .thenReturn(repositoryDetails);

        RepositoryDetails result = githubRepositoryController.getRepositoryDetails(testOwner, testRepositoryName);

        Assert.assertEquals(repositoryDetails, result);
    }

}