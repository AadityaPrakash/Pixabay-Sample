package com.aadi.pixabay.data.network.di

import com.aadi.pixabay.data.network.api.PixabayAPIService
import com.aadi.pixabay.data.repository.ImageSearchRepositoryImpl
import com.aadi.pixabay.domain.utils.Constants
import com.aadi.pixabay.domain.repository.ImageSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideAPIService(retrofit: Retrofit) : PixabayAPIService {
        return  retrofit.create(PixabayAPIService::class.java)
    }

    @Provides
    fun provideImageSearchRepository(pixabayAPIService: PixabayAPIService): ImageSearchRepository {
        return ImageSearchRepositoryImpl(pixabayAPIService = pixabayAPIService)
    }
}


