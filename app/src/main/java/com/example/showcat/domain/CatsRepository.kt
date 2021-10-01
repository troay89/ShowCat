package com.example.showcat.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.showcat.domain.model.CatEntity
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    fun getResults(): Flow<PagingData<CatEntity>>
}
