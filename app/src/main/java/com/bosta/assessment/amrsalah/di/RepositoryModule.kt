package com.bosta.assessment.amrsalah.di

import com.bosta.assessment.amrsalah.data.album.repository.AlbumRepositoryImpl
import com.bosta.assessment.amrsalah.data.profile.repository.ProfileRepositoryImpl
import com.bosta.assessment.amrsalah.domain.album.repository.AlbumRepository
import com.bosta.assessment.amrsalah.domain.profile.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    fun bindsPhotosRepository(
        photosRepositoryImpl: AlbumRepositoryImpl
    ): AlbumRepository
}
