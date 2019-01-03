package com.githubdemo.repository

import com.githubdemo.model.RootElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("/repos/{owner}/{repo}/pulls")
    fun getPullRequestList(@Path("owner") owner: String, @Path("repo") repo: String, @Query("page") page: Int): Call<List<RootElement>>
}