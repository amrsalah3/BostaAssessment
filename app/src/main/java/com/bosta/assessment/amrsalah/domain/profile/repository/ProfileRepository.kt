package com.bosta.assessment.amrsalah.domain.profile.repository

import com.bosta.assessment.amrsalah.domain.Result
import com.bosta.assessment.amrsalah.domain.profile.model.Profile

interface ProfileRepository {
    suspend fun getProfile(): Result<Profile>
}
