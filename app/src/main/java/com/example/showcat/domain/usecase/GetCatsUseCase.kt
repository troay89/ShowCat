package com.example.showcat.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.showcat.data.repository.CatRepositoryImpl
import com.example.showcat.domain.CatsRepository
import com.example.showcat.domain.model.CatEntity
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


class GetCatsUseCase @Inject constructor(private val carRepository: CatsRepository) {

    fun execute(): LiveData<PagingData<CatEntity>>{
        return carRepository.getResults()
    }
}