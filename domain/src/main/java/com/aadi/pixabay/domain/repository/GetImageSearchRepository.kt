package com.aadi.pixabay.domain.repository

import com.aadi.pixabay.domain.models.ImagesModel
import com.aadi.pixabay.domain.models.ResponseModel
import retrofit2.Response

interface GetImageSearchRepository {

    suspend fun getSearchResult(query: String, page: Int, per_page: Int): List<ImagesModel>
}