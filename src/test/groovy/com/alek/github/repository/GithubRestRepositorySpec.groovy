package com.alek.github.repository

import com.alek.github.repository.dto.GithubRepoData
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class GithubRestRepositorySpec extends Specification {

	final String githubReposUri = "test-uri"

	RestTemplate githubRestTemplate

	GithubRestRepository githubRestRepository

	def setup() {
		githubRestTemplate = Mock()
		githubRestRepository = new GithubRestRepository(githubReposUri, githubRestTemplate)
	}

	def "get github repository details"() {
		given: "repository data"
			def testOwner = "testOwner", testRepositoryName = "testRepository"
			def githubRepoData = GithubRepoData.builder()
					.fullName("testRepository")
					.description("testDescription")
					.cloneUrl("https://github.com/test/test.git")
					.stargazersCount(10590)
					.createdAt("2015-03-19T18:21:20Z")
					.build()

		and:
			githubRestTemplate.getForObject(githubReposUri, GithubRepoData.class, testOwner, testRepositoryName) >> githubRepoData

		when:
			def result = githubRestRepository.getGithubRepositoryData(testOwner, testRepositoryName)

		then:
			assert result == githubRepoData
	}
}
