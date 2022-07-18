package com.aadi.pixabay.data.network.models

data class ResponseDTO(
    val hits: List<ImagesDTO>,
    val total: Int,
    val totalHits: Int
)