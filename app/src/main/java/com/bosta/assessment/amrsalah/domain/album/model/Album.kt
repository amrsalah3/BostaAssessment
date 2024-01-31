package com.bosta.assessment.amrsalah.domain.album.model

data class Album(
    val id: Int,
    val title: String,
    val photos: List<Photo>,
    val filteredPhotos: List<Photo> = photos
)

data class Photo(
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

fun List<Photo>.filterByTitle(title: String) =
    filter { it.title.contains(other = title, ignoreCase = true) }
