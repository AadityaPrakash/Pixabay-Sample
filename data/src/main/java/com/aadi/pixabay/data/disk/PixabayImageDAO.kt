package com.aadi.pixabay.data.disk

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aadi.pixabay.domain.models.ImagesModel

@Dao
interface PixabayImageDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllResults(list: List<ImagesModel>)

    @Query("SELECT * FROM PixabayImageDatabase")
    fun getAllItems(): PagingSource<Int, ImagesModel>

    @Query("DELETE FROM PixabayImageDatabase")
    suspend fun deleteAllItems()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllImageKey(l:List<PixabayImageKey>)

    @Query("DELETE FROM PixabayImageKey")
    suspend fun deleteAllImageKey()

    @Query("SELECT * FROM PixabayImageKey WHERE id=:id")
    suspend fun getAllKeys(id:String): PixabayImageKey
}