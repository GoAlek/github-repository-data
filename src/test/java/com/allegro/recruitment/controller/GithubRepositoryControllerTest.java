package com.allegro.recruitment.controller;

import com.allegro.recruitment.controller.dto.RepositoryDetails;
import com.allegro.recruitment.service.GithubRepositoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GithubRepositoryControllerTest {

    @InjectMocks
    private GithubRepositoryController githubRepositoryController;

    @Mock
    private GithubRepositoryService githubRepositoryService;

    @Test
    public void getRepositoryDetails() {
        final String testOwner = "testOwner", testRepositoryName = "testRepository";
        RepositoryDetails repositoryDetails = new RepositoryDetails.RepositoryDetailsBuilder()
                .fullName("testRepository")
                .description("testDescription")
                .cloneUrl("https://github.com/test/test.git")
                .stars(10590)
                .createdAt("2015-03-19T18:21:20Z")
                .build();

        when(githubRepositoryService
                .getRepositoryDetails(testOwner, testRepositoryName))
                .thenReturn(repositoryDetails);

        RepositoryDetails result = githubRepositoryController.getRepositoryDetails(testOwner, testRepositoryName);

        Assert.assertEquals(repositoryDetails, result);
    }

}