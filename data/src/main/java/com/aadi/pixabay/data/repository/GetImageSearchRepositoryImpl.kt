package com.aadi.pixabay.data.repository

import com.aadi.pixabay.data.mappers.toDomain
import com.aadi.pixabay.data.network.api.PixabayAPIService
import com.aadi.pixabay.data.network.api.SafeApiRequest
import com.aadi.pixabay.domain.utils.Config
import com.aadi.pixabay.domain.models.ImagesModel
import com.aadi.pixabay.domain.repository.GetImageSearchRepository
import javax.inject.Inject

class GetImageSearchRepositoryImpl @Inject constructor(private  val pixabayAPIService: PixabayAPIService )
    : GetImageSearchRepository, SafeApiRequest() {

    override suspend fun getSearchResult(query: String, page: Int, per_page: Int): List<ImagesModel> {
        val response = safeApiRequest { pixabayAPIService.getSearchResults(Config.API_KEY, query, page, per_page) }
        return response.hits.toDomain()?: emptyList<ImagesModel>()
    }
}