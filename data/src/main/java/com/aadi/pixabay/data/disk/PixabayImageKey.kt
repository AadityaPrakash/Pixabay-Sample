package com.aadi.pixabay.data.disk

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PixabayImageKey(

    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var prev: Int,
    var next: Int
)
