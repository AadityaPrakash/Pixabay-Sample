package com.aadi.pixabay.domain.di

import com.aadi.pixabay.domain.repository.GetImageSearchRepository
import com.aadi.pixabay.domain.usecases.GetSearchQueryResultsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetSearchQueryResultsUseCase(getImageSearchRepository: GetImageSearchRepository) : GetSearchQueryResultsUseCase {
        return GetSearchQueryResultsUseCase(getImageSearchRepository)
    }


}