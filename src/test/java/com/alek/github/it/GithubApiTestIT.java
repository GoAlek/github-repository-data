package com.alek.github.it;

import com.alek.github.repository.GithubRestRepository;
import com.alek.github.repository.dto.GithubRepoData;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.DefaultResponseCreator;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubApiTestIT {

    private static final String gitHubRootUri = "https://api.github.com";

    private MockRestServiceServer mockServer;

    @Autowired
    @Qualifier("githubRestTemplate")
    private RestTemplate githubRestTemplate;

    @Autowired
    private GithubRestRepository githubRestRepository;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        this.mockServer = MockRestServiceServer.createServer(githubRestTemplate);
    }

    @Test
    public void givenValidRequestToGithubRepositoryApi_whenGetGithubRepository_thenStatus200() throws IOException {
        byte[] responseBody = readAllBytes(get(
                "src",
                "test",
                "resources",
                "mock", "response",
                "remote.server.response.get.repo.200.json"));
        DefaultResponseCreator responseCreator = withStatus(HttpStatus.OK)
                .body(responseBody).contentType(MediaType.APPLICATION_JSON);

        mockServer
                .expect(requestTo(gitHubRootUri + "/repos/google/guava"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(responseCreator);

        GithubRepoData result = githubRestRepository
                .getGithubRepositoryData("google", "guava");

        mockServer.verify();
        assertNotNull(result);
    }

    @Test
    public void givenInvalidRequestToGithubRepositoryApi_whenGetGithubRepository_thenStatus404() {
        DefaultResponseCreator responseCreator = withStatus(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON);

        mockServer
                .expect(requestTo(gitHubRootUri + "/repos/google/non-existent-repo"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(responseCreator);

        thrown.expect(HttpClientErrorException.class);
        thrown.expectMessage("Not Found");

        GithubRepoData result = githubRestRepository
                .getGithubRepositoryData("google", "non-existent-repo");

        mockServer.verify();
        assertNull(result);
    }

}
