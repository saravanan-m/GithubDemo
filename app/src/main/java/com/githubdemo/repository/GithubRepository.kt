package com.githubdemo.repository

import android.arch.lifecycle.MutableLiveData
import com.githubdemo.model.RootElement


interface GithubRepository {
    fun getPullResultLiveData(): MutableLiveData<List<RootElement>>

    fun initSearch(owner: String, repo: String, page: Int)
}