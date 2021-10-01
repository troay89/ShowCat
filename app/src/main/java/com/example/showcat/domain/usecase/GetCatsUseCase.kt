package com.example.showcat.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.showcat.domain.CatsRepository
import com.example.showcat.domain.model.CatEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCatsUseCase @Inject constructor(private val catRepository: CatsRepository) {
    fun execute(): Flow<PagingData<CatEntity>> {
        return catRepository.getResults()
    }
}
