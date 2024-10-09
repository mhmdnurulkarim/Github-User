package com.mhmdnurulkarim.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mhmdnurulkarim.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * from user ORDER BY username ASC")
    fun getFavoriteListUser(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user WHERE username = :username")
    fun getFavoriteDetailState(username: String): Flow<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteUser(user: UserEntity)

    @Delete
    suspend fun deleteFavoriteUser(user: UserEntity): Int
}