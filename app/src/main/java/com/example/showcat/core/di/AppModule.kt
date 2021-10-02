package com.example.showcat.core.di

import com.example.showcat.data.api.retrofit.CatApiService
import com.example.showcat.data.repository.CatRepositoryImpl
import com.example.showcat.data.repository.factory.CatPagingSourceFactory
import com.example.showcat.domain.CatsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providerRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(CatApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): CatApiService = retrofit.create(CatApiService::class.java)

    @Provides
    fun provideDataManager (factory: CatPagingSourceFactory): CatsRepository {
        return CatRepositoryImpl(factory)
    }
}
