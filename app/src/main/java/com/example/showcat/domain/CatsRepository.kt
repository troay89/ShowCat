package com.example.showcat.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.showcat.domain.model.CatEntity

interface CatsRepository {
    fun getResults(): LiveData<PagingData<CatEntity>>
}
