package com.mhmdnurulkarim.core.domain.usecase

import com.mhmdnurulkarim.core.data.Resource
import com.mhmdnurulkarim.core.domain.model.User
import com.mhmdnurulkarim.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(private val userRepository: IUserRepository): UserUseCase {
    override fun searchUser(query: String?): Flow<Resource<List<User>>> = userRepository.searchUser(query)

    override fun getDetailUser(username: String): Flow<Resource<User>> = userRepository.getDetailUser(username)

    override fun getUserFollowers(username: String): Flow<Resource<List<User>>> = userRepository.getUserFollowers(username)

    override fun getUserFollowing(username: String): Flow<Resource<List<User>>> = userRepository.getUserFollowing(username)

    override fun getFavoriteListUser(): Flow<List<User>> = userRepository.getFavoriteListUser()

    override fun getFavoriteDetailState(username: String): Flow<User>? = userRepository.getFavoriteDetailState(username)

    override suspend fun insertFavoriteUser(user: User) = userRepository.insertFavoriteUser(user)

    override suspend fun deleteFavoriteUser(user: User): Int = userRepository.deleteFavoriteUser(user)

    override suspend fun saveThemeSetting(isDarkMode: Boolean) = userRepository.saveThemeSetting(isDarkMode)

    override fun getThemeSetting(): Flow<Boolean> = userRepository.getThemeSetting()
}