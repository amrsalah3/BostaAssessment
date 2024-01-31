package com.bosta.assessment.amrsalah.data.album.model

class PhotosDto : ArrayList<Photo>()

data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
