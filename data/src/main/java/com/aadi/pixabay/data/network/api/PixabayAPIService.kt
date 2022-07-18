package com.aadi.pixabay.data.network.api

import com.aadi.pixabay.data.network.models.ResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPIService {

    @GET("/api/")
    suspend fun getSearchResults(@Query("key") key: String,
                                 @Query("q") query: String,
                                 @Query("page") page: Int,
                                 @Query("per_page") perPage: Int) : Response<ResponseDTO>
}