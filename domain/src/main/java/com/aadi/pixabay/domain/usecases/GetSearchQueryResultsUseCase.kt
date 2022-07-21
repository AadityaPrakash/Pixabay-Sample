package com.aadi.pixabay.domain.usecases

import com.aadi.pixabay.domain.models.ImagesModel
import com.aadi.pixabay.domain.repository.GetImageSearchRepository
import com.aadi.pixabay.domain.utils.Config
import com.aadi.pixabay.domain.utils.Helper.getFormattedSearchQuery
import com.aadi.pixabay.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Exception


class GetSearchQueryResultsUseCase @Inject constructor(private val repo: GetImageSearchRepository){

    operator fun invoke(query: String) : Flow<Resource<List<ImagesModel>>> = flow {
        emit(Resource.Loading(null))

        try {
            val formattedQuery = getFormattedSearchQuery(query)
            val response = repo.getSearchResult(formattedQuery, 1, Config.ITEMS_PER_PAGE)
            emit(Resource.Success(data = response))

        } catch (e: Exception){
            emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
        }
    }
}
