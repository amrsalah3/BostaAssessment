package com.bosta.assessment.amrsalah.data.profile.datasource

import com.bosta.assessment.amrsalah.data.profile.model.AlbumDto
import com.bosta.assessment.amrsalah.data.profile.model.AlbumsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumsRemoteDataSource {

    @GET("users/{userId}/albums")
    suspend fun getAlbums(
        @Path(value = "userId") userId: Int
    ): AlbumsDto

    @GET("albums/{albumId}")
    suspend fun getAlbum(
        @Path(value = "albumId") albumId: Int
    ): AlbumDto
}
