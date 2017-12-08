package com.allegro.recruitment.github.controller;

import com.allegro.recruitment.github.controller.dto.RepositoryDetails;
import com.allegro.recruitment.github.service.GithubRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GithubRepositoryService githubRepositoryService;

    @Test
    public void getRepositoryDetails_ReturnProperResponse_IfOwnerAndRepositoryAreValid() throws Exception {
        final String testOwner = "testOwner", testRepositoryName = "testRepository";
        RepositoryDetails repositoryDetails = RepositoryDetails.builder()
                .fullName("testRepository")
                .description("testDescription")
                .cloneUrl("https://github.com/test/test.git")
                .stars(10590)
                .createdAt("2015-03-19T18:21:20Z")
                .build();

        given(githubRepositoryService.getGithubRepositoryDetails(testOwner, testRepositoryName))
                .willReturn(repositoryDetails);

        mockMvc.perform(get("/repositories/testOwner/testRepository")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isMap())
            .andExpect(jsonPath("$.fullName", is(repositoryDetails.getFullName())))
            .andExpect(jsonPath("$.description", is(repositoryDetails.getDescription())))
            .andExpect(jsonPath("$.cloneUrl", is(repositoryDetails.getCloneUrl())))
            .andExpect(jsonPath("$.stars", is(repositoryDetails.getStars())))
            .andExpect(jsonPath("$.createdAt", is(repositoryDetails.getCreatedAt())));
    }
}