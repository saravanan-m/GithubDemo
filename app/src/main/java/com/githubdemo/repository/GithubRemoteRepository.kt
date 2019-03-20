package com.githubdemo.repository

import com.githubdemo.model.RootElement
import retrofit2.Retrofit

class GithubRemoteRepository(var retrofit: Retrofit) : GithubRepository {

    override fun initSearch(owner: String, repo: String, page: Int): List<RootElement>? {
        val githubService = retrofit.create(GithubService::class.java)
        val reposne = githubService.getPullRequestList(owner, repo, page).execute()
        return reposne.body()
    }
}