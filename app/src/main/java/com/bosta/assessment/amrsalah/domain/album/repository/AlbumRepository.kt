package com.bosta.assessment.amrsalah.domain.album.repository

import com.bosta.assessment.amrsalah.domain.Result
import com.bosta.assessment.amrsalah.domain.album.model.Album

interface AlbumRepository {
    suspend fun getAlbum(albumId: Int): Result<Album>
}
