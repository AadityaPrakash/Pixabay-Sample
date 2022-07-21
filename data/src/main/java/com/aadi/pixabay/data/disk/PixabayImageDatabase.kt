package com.aadi.pixabay.data.disk

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aadi.pixabay.domain.models.ImagesModel

@Database(entities = [ImagesModel::class, PixabayImageKey::class], version = 1, exportSchema = false)
abstract class PixabayImageDatabase : RoomDatabase() {

    companion object {
        fun getInstance(mContext: Context) : PixabayImageDatabase {
            return  Room.databaseBuilder(mContext, PixabayImageDatabase::class.java, "PixabayImageDatabase").build()
        }
    }

    abstract fun getPixabayImageDAO(): PixabayImageDAO
}