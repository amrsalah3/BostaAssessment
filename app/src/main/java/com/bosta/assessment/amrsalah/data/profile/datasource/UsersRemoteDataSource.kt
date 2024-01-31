package com.bosta.assessment.amrsalah.data.profile.datasource

import com.bosta.assessment.amrsalah.data.profile.model.UsersDto
import retrofit2.http.GET

interface UsersRemoteDataSource {

    @GET("users")
    suspend fun getUsers(): UsersDto
}
