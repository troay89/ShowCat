package com.example.showcat.ui.mapper

import com.example.showcat.core.BaseMapper
import com.example.showcat.domain.model.CatEntity
import com.example.showcat.ui.model.CatUI

object EntityToUI: BaseMapper<CatEntity, CatUI> {
    override fun map(type: CatEntity?): CatUI {
        return CatUI(
            id = type!!.id,
            url = type.url
        )
    }
}
