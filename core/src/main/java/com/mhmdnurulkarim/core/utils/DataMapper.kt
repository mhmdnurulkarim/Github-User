package com.mhmdnurulkarim.core.utils

import com.mhmdnurulkarim.core.data.source.local.entity.UserEntity
import com.mhmdnurulkarim.core.data.source.remote.response.UserResponse
import com.mhmdnurulkarim.core.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {
    fun mapResponsesToDomain(input: List<UserResponse>): Flow<List<User>> {
        val dataArray = ArrayList<User>()
        input.map {
            val user = User(
                it.id,
                it.login,
                it.name,
                it.following,
                it.followers,
                it.publicRepos,
                it.avatarUrl,
                false
            )
            dataArray.add(user)
        }
        return flowOf(dataArray)
    }

    fun mapResponseToDomain(input: UserResponse): Flow<User> {
        return flowOf(
            User(
                input.id,
                input.login,
                input.name,
                input.following,
                input.followers,
                input.publicRepos,
                input.avatarUrl,
                false
            )
        )
    }

    fun mapEntitiesToDomain(input: List<UserEntity>): List<User> =
        input.map { userEntity ->
            User(
                userEntity.id,
                userEntity.login,
                userEntity.name,
                userEntity.following,
                userEntity.followers,
                userEntity.publicRepos,
                userEntity.avatarUrl,
                userEntity.isFavorite
            )
        }

    fun mapEntityToDomain(input: UserEntity?): User {
        return User(
            input?.id,
            input?.login,
            input?.name,
            input?.following,
            input?.followers,
            input?.publicRepos,
            input?.avatarUrl,
            input?.isFavorite
        )
    }

    fun mapDomainToEntity(input: User) = UserEntity(
        input.id,
        input.login,
        input.name,
        input.followers,
        input.following,
        input.publicRepos,
        input.avatarUrl,
        input.isFavorite
    )
}