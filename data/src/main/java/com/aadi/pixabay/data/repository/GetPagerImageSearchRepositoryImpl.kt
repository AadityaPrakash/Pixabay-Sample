package com.aadi.pixabay.data.repository

import androidx.paging.PagingSource
import com.aadi.pixabay.data.disk.PixabayImageDAO
import com.aadi.pixabay.domain.models.ImagesModel
import javax.inject.Inject

class GetPagerImageSearchRepositoryImpl @Inject constructor(private val dao: PixabayImageDAO){

    fun getAllRecords(): PagingSource<Int, ImagesModel> {
        return dao.getAllItems()
    }

    suspend fun insertRecord(imagesModel: ImagesModel) {
        dao.insertRecord(imagesModel)
    }

    suspend fun insertAllRecords(list: List<ImagesModel>){
        dao.insertAllResults(list)
    }
}