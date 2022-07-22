package com.aadi.pixabay.domain.utils

import com.aadi.pixabay.domain.BuildConfig

object Config {
    const val BASE_URL = "https://pixabay.com/"
    const val API_KEY = BuildConfig.API_KEY

    const val TRIGGER_KEYWORD = "fruits"
    const val ITEMS_PER_PAGE = 30
}