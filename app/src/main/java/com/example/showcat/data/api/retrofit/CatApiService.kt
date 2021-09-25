package com.example.showcat.data.api.retrofit

import com.example.showcat.data.api.model.CatApi
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatApiService {
    companion object {
        const val BASE_URL = "https://api.thecatapi.com/"
        const val CLIENT_ID = "2f448567-9493-4a86-8494-cc1bef7f193e"
    }

    @GET("v1/images/search?")
    @Headers("x-api-key: $CLIENT_ID")
    suspend fun getPhoto(
    @Query("limit") limit: Int,
    @Query("page") page: Int
    ): List<CatApi>

}
