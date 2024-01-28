package com.mhmdnurulkarim.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mhmdnurulkarim.core.data.source.local.entity.GithubUserEntity

@Dao
interface GithubUserDao {

    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun getFavoriteDetailUser(username: String): GithubUserEntity

    @Query("SELECT * FROM user ORDER BY username ASC")
    suspend fun getFavoriteListUser(): List<GithubUserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteUser(user: GithubUserEntity)

    @Delete
    suspend fun deleteFavoriteUser(user: GithubUserEntity)
}