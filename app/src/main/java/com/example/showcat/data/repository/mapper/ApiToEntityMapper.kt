package com.example.showcat.data.repository.mapper

import com.example.showcat.core.BaseMapper
import com.example.showcat.data.api.model.CatApi
import com.example.showcat.domain.model.CatEntity

object ApiToEntityMapper : BaseMapper <List<CatApi>, List<CatEntity>> {

    override fun map(type: List<CatApi>?): List<CatEntity> {
        return type?.map {
            CatEntity(
                id = it.id,
                url = it.url
            )
        } ?: listOf()
    }
}
