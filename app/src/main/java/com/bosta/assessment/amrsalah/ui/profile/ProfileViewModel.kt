package com.bosta.assessment.amrsalah.ui.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosta.assessment.amrsalah.domain.Result
import com.bosta.assessment.amrsalah.domain.profile.model.Profile
import com.bosta.assessment.amrsalah.domain.profile.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<Profile>>(Result.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        updateProfileState()
    }

    private fun updateProfileState() = viewModelScope.launch {
        _uiState.value = Result.Loading

        val result = profileRepository.getProfile()
        when (result) {
            Result.Loading -> {}
            is Result.Success -> _uiState.value = Result.Success(result.data)
            is Result.Failure -> _uiState.value = Result.Failure(result.exception)
        }
    }
}
