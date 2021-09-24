package com.example.showcat.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.showcat.domain.CatsRepository
import com.example.showcat.domain.model.CatEntity


class GetCatsUseCase(private val carRepository: CatsRepository) {

    suspend fun execute(): LiveData<PagingData<CatEntity>> {
        return carRepository.getResults()
    }
}