package com.example.showcat.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.showcat.data.api.model.CatApi

interface CatsRepository {

    fun getResults(): LiveData<PagingData<CatApi>>
}