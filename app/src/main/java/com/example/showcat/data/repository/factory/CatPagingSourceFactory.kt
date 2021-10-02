package com.example.showcat.data.repository.factory

import com.example.showcat.data.api.retrofit.CatApiService
import com.example.showcat.data.repository.CatPagingSource
import javax.inject.Inject

class CatPagingSourceFactory@Inject constructor(private val catApiService: CatApiService) {

    fun create() = CatPagingSource(catApiService)
}
