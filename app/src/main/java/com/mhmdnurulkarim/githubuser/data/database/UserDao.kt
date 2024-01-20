package com.mhmdnurulkarim.githubuser.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mhmdnurulkarim.githubuser.data.network.DetailUserResponse

@Dao
interface UserDao {

    @Query("SELECT * FROM user ORDER BY username ASC")
    suspend fun getFavoriteListUser(): List<DetailUserResponse>

    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun getFavoriteDetailUser(username: String): DetailUserResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteUser(user: DetailUserResponse)

    @Delete
    suspend fun deleteFavoriteUser(user: DetailUserResponse)
}