package com.aadi.pixabay.data.di

import android.content.Context
import com.aadi.pixabay.data.disk.PixabayImageDAO
import com.aadi.pixabay.data.disk.PixabayImageDatabase
import com.aadi.pixabay.data.network.api.PixabayAPIService
import com.aadi.pixabay.data.repository.GetImageSearchRepositoryImpl
import com.aadi.pixabay.domain.utils.Config
import com.aadi.pixabay.domain.repository.GetImageSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit) : PixabayAPIService {
        return  retrofit.create(PixabayAPIService::class.java)
    }

    @Singleton
    @Provides
    fun provideImageSearchRepository(pixabayAPIService: PixabayAPIService): GetImageSearchRepository {
        return GetImageSearchRepositoryImpl(pixabayAPIService = pixabayAPIService)
    }

    @Singleton
    @Provides
    fun provideImageDatabase(@ApplicationContext mContext: Context) : PixabayImageDatabase {
        return PixabayImageDatabase.getInstance(mContext)
    }

    @Singleton
    @Provides
    fun provideDAO(database: PixabayImageDatabase): PixabayImageDAO {
        return database.getPixabayImageDAO()
    }

}


