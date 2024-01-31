package com.bosta.assessment.amrsalah.domain.profile.model

import com.bosta.assessment.amrsalah.domain.album.model.Album

data class Profile(
    val id: Int,
    val username: String,
    val address: String,
    val albums: List<Album>
)
