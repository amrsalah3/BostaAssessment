package com.bosta.assessment.amrsalah.data.profile.model

class AlbumsDto : ArrayList<AlbumDto>()

data class AlbumDto(
    val userId: Int,
    val id: Int,
    val title: String
)
