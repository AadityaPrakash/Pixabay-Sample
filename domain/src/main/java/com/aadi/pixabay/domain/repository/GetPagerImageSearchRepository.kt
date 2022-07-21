package com.aadi.pixabay.domain.repository

import androidx.paging.PagingSource
import com.aadi.pixabay.domain.models.ImagesModel

interface GetPagerImageSearchRepository {

    fun getAllRecords() : PagingSource<Int, ImagesModel>

    suspend fun insertRecord(imagesModel: ImagesModel)

    suspend fun insertAllRecords(list: List<ImagesModel>)
}