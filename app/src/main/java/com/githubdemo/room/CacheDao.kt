package com.githubdemo.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.provider.SyncStateContract.Helpers.update


@Dao
abstract class CacheDao {
    @Query("select * from cache_table where cache_key = :key")
    abstract fun getCacheData(key: String): CacheTable?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertItem(executingFile: CacheTable): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract fun updateItem(executingFile: CacheTable)

    fun upsert(executingFile: CacheTable) {
        val id = insertItem(executingFile)
        if (id.toInt() == -1) {
            updateItem(executingFile)
        }
    }
}