package com.bosta.assessment.amrsalah.data.album.repository

import com.bosta.assessment.amrsalah.data.album.datasource.PhotosRemoteDataSource
import com.bosta.assessment.amrsalah.data.album.model.PhotosDto
import com.bosta.assessment.amrsalah.data.profile.datasource.AlbumsRemoteDataSource
import com.bosta.assessment.amrsalah.data.profile.model.AlbumDto
import com.bosta.assessment.amrsalah.domain.Result
import com.bosta.assessment.amrsalah.domain.album.model.Album
import com.bosta.assessment.amrsalah.domain.album.model.Photo
import com.bosta.assessment.amrsalah.domain.album.repository.AlbumRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRepositoryImpl @Inject constructor(
    private val photosRemoteDataSource: PhotosRemoteDataSource,
    private val albumsRemoteDataSource: AlbumsRemoteDataSource,
    private val dispatcher: CoroutineDispatcher
) : AlbumRepository {

    override suspend fun getAlbum(albumId: Int): Result<Album> = withContext(dispatcher) {
        try {
            val albumDto = getAlbumDetails(albumId)
            val photosDto = getAlbumPhotos(albumId)

            val album = Album(
                id = albumDto.id,
                title = albumDto.title,
                photos = photosDto.map {
                    Photo(
                        id = it.id,
                        title = it.title,
                        url = it.url,
                        thumbnailUrl = it.thumbnailUrl
                    )
                }
            )
            return@withContext Result.Success(album)
        } catch (exception: Exception) {
            return@withContext Result.Failure(exception)
        }
    }

    private suspend fun getAlbumDetails(albumId: Int): AlbumDto = withContext(dispatcher) {
        return@withContext albumsRemoteDataSource.getAlbum(albumId)
    }

    private suspend fun getAlbumPhotos(albumId: Int): PhotosDto = withContext(dispatcher) {
        return@withContext photosRemoteDataSource.getAlbumPhotos(albumId)
    }
}
