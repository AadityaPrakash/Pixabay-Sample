package com.aadi.pixabay.domain.di

import com.aadi.pixabay.domain.repository.GetImageSearchRepository
import com.aadi.pixabay.domain.usecases.GetDefaultKeywordResultUseCase
import com.aadi.pixabay.domain.usecases.GetSearchQueryResultsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetSearchQueryResultsUseCase(repo: GetImageSearchRepository) : GetSearchQueryResultsUseCase {
        return GetSearchQueryResultsUseCase(repo)
    }

    @Provides
    fun provideGetDefaultKeywordResultUseCase(repo: GetImageSearchRepository) : GetDefaultKeywordResultUseCase {
        return GetDefaultKeywordResultUseCase(repo)
    }


}