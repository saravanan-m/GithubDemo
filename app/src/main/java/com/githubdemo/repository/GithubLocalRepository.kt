package com.githubdemo.repository

import com.githubdemo.model.RootElement
import com.githubdemo.room.CacheDao
import com.githubdemo.room.CacheTable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GithubLocalRepository(val cacheDao: CacheDao) : GithubRepository {

    override fun initSearch(owner: String, repo: String, page: Int): List<RootElement>? {
        val cachetable = cacheDao.getCacheData(owner + "|" + repo + ":" + page)

        var result: List<RootElement>? = null
        if (cachetable != null && (System.currentTimeMillis() - cachetable.created) <= 30 * 60 * 1000) {
            val turnsType = object : TypeToken<List<RootElement>>() {}.type
            val gson = Gson()
            result = gson.fromJson<List<RootElement>>(cachetable.value, turnsType)
        }
        return result
    }

    fun saveData(owner: String, repo: String, page: Int, data: String) {
        cacheDao.upsert(
            CacheTable(
                key = owner + "|" + repo + ":" + page,
                value = data,
                created = System.currentTimeMillis()
            )
        )
    }
}