package com.aadi.pixabaysample.screens.home

import com.aadi.pixabay.domain.models.ImagesModel

data class HomeState (

    var isLoading: Boolean = false,
    var data: List<ImagesModel>? = null,
    var error: String = ""
)
