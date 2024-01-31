package com.bosta.assessment.amrsalah.di

import com.bosta.assessment.amrsalah.data.album.datasource.PhotosRemoteDataSource
import com.bosta.assessment.amrsalah.data.profile.datasource.AlbumsRemoteDataSource
import com.bosta.assessment.amrsalah.data.profile.datasource.UsersRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideRetrofitClient(): Retrofit {
        val baseUrl = "https://jsonplaceholder.typicode.com/"

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    @Provides
    fun provideUsersRemoteDataSource(retrofit: Retrofit) =
        retrofit.create(UsersRemoteDataSource::class.java)

    @Provides
    fun provideAlbumsRemoteDataSource(retrofit: Retrofit) =
        retrofit.create(AlbumsRemoteDataSource::class.java)

    @Provides
    fun providePhotosRemoteDataSource(retrofit: Retrofit) =
        retrofit.create(PhotosRemoteDataSource::class.java)
}
