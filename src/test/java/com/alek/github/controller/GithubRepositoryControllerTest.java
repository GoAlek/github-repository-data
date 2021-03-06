package com.alek.github.controller;

import com.alek.github.controller.dto.RepositoryDetails;
import com.alek.github.exception.GithubRepositoryRetrievalException;
import com.alek.github.service.GithubRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GithubRepositoryController.class)
public class GithubRepositoryControllerTest {
    private final String testOwner = "testOwner";
    private final String testRepositoryName = "testRepository";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GithubRepositoryService githubRepositoryService;

    @Test
    public void getRepositoryDetails_ReturnProperResponse_IfOwnerAndRepositoryAreValid() throws Exception {
        RepositoryDetails repositoryDetails = RepositoryDetails.builder()
                .fullName("testRepository")
                .description("testDescription")
                .cloneUrl("https://github.com/test/test.git")
                .stars(10590)
                .createdAt("2015-03-19T18:21:20Z")
                .build();

        given(githubRepositoryService.getGithubRepositoryDetails(testOwner, testRepositoryName))
                .willReturn(repositoryDetails);

        mockMvc.perform(get(String.format("/repositories/%s/%s", testOwner, testRepositoryName))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isMap())
            .andExpect(jsonPath("$.fullName", is(repositoryDetails.getFullName())))
            .andExpect(jsonPath("$.description", is(repositoryDetails.getDescription())))
            .andExpect(jsonPath("$.cloneUrl", is(repositoryDetails.getCloneUrl())))
            .andExpect(jsonPath("$.stars", is(repositoryDetails.getStars())))
            .andExpect(jsonPath("$.createdAt", is(repositoryDetails.getCreatedAt())));
    }

    @Test
    public void getRepositoryDetails_ReturnErrorResponse_IfGithubReturns500() throws Exception {
        given(githubRepositoryService.getGithubRepositoryDetails(testOwner, testRepositoryName))
                .willThrow(
                        new GithubRepositoryRetrievalException(
                                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                testOwner,
                                testRepositoryName));

        mockMvc.perform(get(String.format("/repositories/%s/%s", testOwner, testRepositoryName))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is5xxServerError())
            .andExpect(jsonPath("$").isMap())
            .andExpect(jsonPath("$.status", is(HttpStatus.INTERNAL_SERVER_ERROR.name())))
            .andExpect(jsonPath("$.requestParameters.repositoryOwner", is(testOwner)))
            .andExpect(jsonPath("$.requestParameters.repositoryName", is(testRepositoryName)));
    }

    @Test
    public void getRepositoryDetails_ReturnErrorResponse_IfGithubReturns400() throws Exception {
        given(githubRepositoryService.getGithubRepositoryDetails(testOwner, testRepositoryName))
                .willThrow(
                        new GithubRepositoryRetrievalException(
                            HttpStatus.BAD_REQUEST.name(),
                            HttpStatus.BAD_REQUEST,
                            testOwner,
                            testRepositoryName));

        mockMvc.perform(get(String.format("/repositories/%s/%s", testOwner, testRepositoryName))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.name())))
                .andExpect(jsonPath("$.requestParameters.repositoryOwner", is(testOwner)))
                .andExpect(jsonPath("$.requestParameters.repositoryName", is(testRepositoryName)));
    }

    @Test
    public void getRepositoryDetails_ReturnErrorResponse_IfGithubReturns408() throws Exception {
        given(githubRepositoryService.getGithubRepositoryDetails(testOwner, testRepositoryName))
                .willThrow(
                        new GithubRepositoryRetrievalException(
                            HttpStatus.REQUEST_TIMEOUT.name(),
                            HttpStatus.REQUEST_TIMEOUT,
                            testOwner,
                            testRepositoryName));

        mockMvc.perform(get(String.format("/repositories/%s/%s", testOwner, testRepositoryName))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isRequestTimeout())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.status", is(HttpStatus.REQUEST_TIMEOUT.name())))
                .andExpect(jsonPath("$.requestParameters.repositoryOwner", is(testOwner)))
                .andExpect(jsonPath("$.requestParameters.repositoryName", is(testRepositoryName)));
    }
}