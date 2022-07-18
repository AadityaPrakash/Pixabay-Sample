package com.aadi.pixabay.data.mappers

import com.aadi.pixabay.data.network.models.ImagesDTO
import com.aadi.pixabay.domain.models.ImagesModel

fun List<ImagesDTO>.toDomain(): List<ImagesModel> {

    return map {
        ImagesModel(
            collections = it.collections,
            comments = it.comments,
            downloads = it.downloads,
            id = it.id,
            imageHeight = it.imageHeight,
            imageSize = it.imageSize,
            imageWidth = it.imageWidth,
            largeImageURL = it.largeImageURL,
            likes = it.likes,
            pageURL = it.pageURL,
            previewHeight = it.previewHeight,
            previewURL = it.previewURL,
            previewWidth = it.previewWidth,
            tags = it.tags,
            type = it.type,
            user = it.user,
            userImageURL = it.userImageURL,
            user_id = it.user_id,
            views = it.views,
            webformatHeight = it.webformatHeight,
            webformatURL = it.webformatURL,
            webformatWidth = it.webformatWidth
        )
    }
}