package com.mhmdnurulkarim.core.data

import com.mhmdnurulkarim.core.data.source.NetworkResource
import com.mhmdnurulkarim.core.data.source.local.LocalDataSource
import com.mhmdnurulkarim.core.data.source.remote.RemoteDataSource
import com.mhmdnurulkarim.core.data.source.remote.network.ApiResponse
import com.mhmdnurulkarim.core.data.source.remote.response.UserResponse
import com.mhmdnurulkarim.core.domain.model.User
import com.mhmdnurulkarim.core.domain.repository.IUserRepository
import com.mhmdnurulkarim.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IUserRepository {
    override fun searchUser(query: String?): Flow<Resource<List<User>>> {
        return object : NetworkResource<List<User>, List<UserResponse>>() {
            override fun loadFromNetwork(data: List<UserResponse>): Flow<List<User>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.searchUser(query)
            }

        }.asFlow()
    }

    override fun getDetailUser(username: String): Flow<Resource<User>> {
        return object : NetworkResource<User, UserResponse>() {
            override fun loadFromNetwork(data: UserResponse): Flow<User> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<UserResponse>> {
                return remoteDataSource.getDetailUser(username)
            }

        }.asFlow()
    }

    override fun getUserFollowers(username: String): Flow<Resource<List<User>>> {
        return object : NetworkResource<List<User>, List<UserResponse>>() {
            override fun loadFromNetwork(data: List<UserResponse>): Flow<List<User>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getUserFollowers(username)
            }

        }.asFlow()
    }

    override fun getUserFollowing(username: String): Flow<Resource<List<User>>> {
        return object : NetworkResource<List<User>, List<UserResponse>>() {
            override fun loadFromNetwork(data: List<UserResponse>): Flow<List<User>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getUserFollowing(username)
            }
        }.asFlow()
    }

    override fun getFavoriteListUser(): Flow<List<User>> {
        return localDataSource.getFavoriteListUser().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteDetailUser(username: String): Flow<User> {
        return localDataSource.getFavoriteDetailUser(username).map {
            DataMapper.mapEntityToDomain(it)
        }
    }

    override suspend fun insertFavoriteUser(user: User) {
        val domainUser = DataMapper.mapDomainToEntity(user)
        return localDataSource.insertFavoriteUser(domainUser)
    }

    override suspend fun deleteFavoriteUser(user: User) : Int {
        val domainUser = DataMapper.mapDomainToEntity(user)
        return localDataSource.deleteFavoriteUser(domainUser)
    }
}