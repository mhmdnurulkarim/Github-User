package com.mhmdnurulkarim.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mhmdnurulkarim.core.data.source.local.entity.GithubUserEntity

@Database(entities = [GithubUserEntity::class], exportSchema = false, version = 1)
abstract class GithubUserDatabase : RoomDatabase() {

    abstract fun githubUserDao(): GithubUserDao
}