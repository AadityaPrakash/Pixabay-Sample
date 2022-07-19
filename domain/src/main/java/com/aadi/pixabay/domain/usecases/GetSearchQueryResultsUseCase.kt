package com.aadi.pixabay.domain.usecases

import com.aadi.pixabay.domain.models.ImagesModel
import com.aadi.pixabay.domain.repository.GetImageSearchRepository
import com.aadi.pixabay.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class GetSearchQueryResultsUseCase @Inject constructor(private val getImageSearchRepository: GetImageSearchRepository){

    suspend operator fun invoke() : Flow<Resource<List<ImagesModel>>> = flow {
        emit(Resource.Loading(null))

        try {
            val response = getImageSearchRepository.getSearchResult("fruits", 1, 10)
            emit(Resource.Success(data = response))

        } catch (e: Exception){
            emit(Resource.Error("Error Occurred"))
        }
    }
}