package com.example.showcat.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.showcat.data.repository.factory.CatPagingSourceFactory
import com.example.showcat.domain.CatsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatRepositoryImpl @Inject constructor(private val factory: CatPagingSourceFactory): CatsRepository {
    override fun getResults() = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 50,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { factory.create() }
    ).flow
}
