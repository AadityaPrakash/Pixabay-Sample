package com.aadi.pixabay.domain.utils

class Helper {

    companion object {

        fun getFormattedSearchQuery(query: String): String {
            val q = query
                .trim()
                .split("\\p{Space}".toRegex())
                .toTypedArray()

            return q.joinToString(separator = "+")
        }
    }
}