package com.bosta.assessment.amrsalah.data.album.datasource

import com.bosta.assessment.amrsalah.data.album.model.PhotosDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotosRemoteDataSource {

    @GET("albums/{albumId}/photos")
    suspend fun getAlbumPhotos(
        @Path(value = "albumId") albumId: Int
    ): PhotosDto
}
