package com.alek.github.service

import com.alek.github.repository.GithubRestRepository
import com.alek.github.repository.dto.GithubRepoData
import spock.lang.Specification

class GithubRepositoryServiceSpec extends Specification {

	private GithubRepositoryService githubRepositoryService

	GithubRestRepository githubRestRepository

	def setup() {
		githubRestRepository = Mock(GithubRestRepository.class)
		githubRepositoryService = new GithubRepositoryService(githubRestRepository)
	}

	def "get repository details"() {
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
			githubRestRepository.getGithubRepositoryData(testOwner, testRepositoryName) >> githubRepoData

		when: "requesting for repository details"
			def result = githubRepositoryService.getGithubRepositoryDetails(testOwner, testRepositoryName)

		then: "repository data is returned"
			result.getFullName() == githubRepoData.getFullName()
			result.getDescription() == githubRepoData.getDescription()
			result.getCloneUrl() == githubRepoData.getCloneUrl()
			result.getStars() == githubRepoData.getStargazersCount()
			result.getCreatedAt() == githubRepoData.getCreatedAt()
	}
}
