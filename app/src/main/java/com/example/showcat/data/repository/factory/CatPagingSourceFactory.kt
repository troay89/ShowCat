package com.example.showcat.data.repository.factory

import com.example.showcat.data.api.retrofit.CatApiService
import com.example.showcat.data.repository.CatPagingSource

class CatPagingSourceFactory(private val catApiService: CatApiService) {

    fun create() = CatPagingSource(catApiService)
}
