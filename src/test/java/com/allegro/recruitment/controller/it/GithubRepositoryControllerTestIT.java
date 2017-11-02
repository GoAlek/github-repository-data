package com.allegro.recruitment.controller.it;

import com.allegro.recruitment.controller.dto.RepositoryDetails;
import com.allegro.recruitment.dto.ApiErrorResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubRepositoryControllerTestIT {

    private static final String controllerPath = "/repositories";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getRepositoryDetails_shouldReturnDetailsIfRepositoryExists() {
        ResponseEntity<RepositoryDetails> responseEntity = restTemplate
                .getForEntity(
                        controllerPath + "/{owner}/{repository-name}",
                        RepositoryDetails.class,
                        "google", "guava");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertThat(responseEntity.getBody(), instanceOf(RepositoryDetails.class));
    }

    @Test
    public void getRepositoryDetails_shouldReturnNotFoundIfRepositoryDoesNotExist() {
        ResponseEntity<ApiErrorResponse> responseEntity = restTemplate
                .getForEntity(
                        controllerPath + "/{owner}/{repository-name}",
                        ApiErrorResponse.class,
                        "google", "strange-repo");

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertThat(responseEntity.getBody(), instanceOf(ApiErrorResponse.class));
    }
}
