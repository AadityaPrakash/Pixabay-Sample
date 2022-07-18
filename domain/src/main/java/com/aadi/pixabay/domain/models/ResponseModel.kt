package com.aadi.pixabay.domain.models

data class ResponseModel(
    val hits: List<ImagesModel>,
    val total: Int,
    val totalHits: Int
)
