package com.example.showcat.data.repository.mapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.paging.PagingData
import com.example.showcat.core.BaseMapper
import com.example.showcat.data.api.model.CatApi
import com.example.showcat.domain.model.CatEntity

object ApiToEntityMapper : BaseMapper<LiveData<PagingData<CatApi>>, LiveData<PagingData<CatEntity>>> {
    override fun map(type: LiveData<PagingData<CatApi>>?): LiveData<PagingData<CatEntity>> {
        return type?.map {

        } ?: liveData{ }
    }
}