package com.bosta.assessment.amrsalah.ui.album

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosta.assessment.amrsalah.BostaAssessmentDestinationArgs.ALBUM_ID_ARG
import com.bosta.assessment.amrsalah.domain.Result
import com.bosta.assessment.amrsalah.domain.album.model.Album
import com.bosta.assessment.amrsalah.domain.album.model.filterByTitle
import com.bosta.assessment.amrsalah.domain.album.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val photosRepository: AlbumRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val albumId: Int = savedStateHandle[ALBUM_ID_ARG]!!

    private val _uiState = MutableStateFlow<Result<Album>>(Result.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        updateAlbumState()
    }

    private fun updateAlbumState() = viewModelScope.launch {
        _uiState.value = Result.Loading

        val result = photosRepository.getAlbum(albumId)
        when (result) {
            Result.Loading -> {}
            is Result.Success -> _uiState.value = Result.Success(result.data)
            is Result.Failure -> _uiState.value = Result.Failure(result.exception)
        }
    }

    fun filterPhotosByTitle(title: String) = viewModelScope.launch {
        val latestResult = _uiState.value
        if (latestResult is Result.Success<Album>) {
            withContext(Dispatchers.IO) {
                val album = latestResult.data

                val filteredPhotos = album.photos.filterByTitle(title)

                _uiState.update {
                    Result.Success(album.copy(filteredPhotos = filteredPhotos))
                }
            }
        }
    }
}
