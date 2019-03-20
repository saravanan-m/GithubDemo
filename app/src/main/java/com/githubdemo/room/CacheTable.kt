package com.githubdemo.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "cache_table")
data class CacheTable(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "cache_key") var key: String = "",
        @ColumnInfo(name = "cache_file") var value: String = "",
        @ColumnInfo(name = "created_at") var created: Long = 0
)