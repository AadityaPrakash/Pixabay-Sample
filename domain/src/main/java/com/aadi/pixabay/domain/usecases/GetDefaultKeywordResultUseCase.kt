package com.aadi.pixabay.domain.usecases

import com.aadi.pixabay.domain.models.ImagesModel
import com.aadi.pixabay.domain.repository.GetImageSearchRepository
import com.aadi.pixabay.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetDefaultKeywordResultUseCase @Inject constructor(private val repository: GetImageSearchRepository) {

    operator fun invoke() : Flow<Resource<List<ImagesModel>>> = flow {
        emit(Resource.Loading(null))

        try {
            val response = repository.getSearchResult("fruits", 1, 20)
            emit(Resource.Success(data = response))

        } catch (e: Exception){
            emit(Resource.Error("Error Occurred"))
        }
    }
}