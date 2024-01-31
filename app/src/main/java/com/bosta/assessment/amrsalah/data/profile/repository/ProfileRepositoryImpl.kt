package com.bosta.assessment.amrsalah.data.profile.repository

import com.bosta.assessment.amrsalah.data.profile.datasource.AlbumsRemoteDataSource
import com.bosta.assessment.amrsalah.data.profile.datasource.UsersRemoteDataSource
import com.bosta.assessment.amrsalah.data.profile.model.AlbumDto
import com.bosta.assessment.amrsalah.data.profile.model.UserDto
import com.bosta.assessment.amrsalah.domain.Result
import com.bosta.assessment.amrsalah.domain.album.model.Album
import com.bosta.assessment.amrsalah.domain.profile.model.Profile
import com.bosta.assessment.amrsalah.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val usersDataSource: UsersRemoteDataSource,
    private val albumsDataSource: AlbumsRemoteDataSource,
    private val dispatcher: CoroutineDispatcher
) : ProfileRepository {

    override suspend fun getProfile(): Result<Profile> = withContext(dispatcher) {
        try {
            val userDto = getUser()
            val albumsDto = getAlbums(userDto.id)

            val profile = Profile(
                id = userDto.id,
                username = userDto.username,
                address = userDto.address.run { "$street, $suite, $city, $zipcode" },
                albums = albumsDto.map {
                    Album(
                        id = it.id,
                        title = it.title,
                        photos = emptyList()
                    )
                },
            )
            return@withContext Result.Success(profile)
        } catch (exception: Exception) {
            return@withContext Result.Failure(exception)
        }
    }

    private suspend fun getUser(): UserDto = withContext(dispatcher) {
        return@withContext usersDataSource.getUsers().random()
    }

    private suspend fun getAlbums(userId: Int): List<AlbumDto> = withContext(dispatcher) {
        return@withContext albumsDataSource.getAlbums(userId)
    }
}
