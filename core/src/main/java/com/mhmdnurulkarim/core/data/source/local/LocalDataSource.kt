package com.mhmdnurulkarim.core.data.source.local

import com.mhmdnurulkarim.core.data.source.local.entity.UserEntity
import com.mhmdnurulkarim.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: UserDao) {

    fun getFavoriteListUser(): Flow<List<UserEntity>> = dao.getFavoriteListUser()

    fun getFavoriteDetailState(username: String): Flow<UserEntity> =
        dao.getFavoriteDetailState(username)

    suspend fun insertFavoriteUser(user: UserEntity) = dao.insertFavoriteUser(user)

    suspend fun deleteFavoriteUser(user: UserEntity) = dao.deleteFavoriteUser(user)
}