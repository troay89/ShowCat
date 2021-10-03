package com.example.showcat.ui.firstscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.showcat.domain.usecase.GetCatsUseCase
import com.example.showcat.ui.mapper.EntityToUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    getCatsUseCase: GetCatsUseCase
) : ViewModel() {
    val photos = getCatsUseCase.execute()
        .cachedIn(viewModelScope)
        .map { pagingData ->
            pagingData.map { catEntity ->
                EntityToUI.map(catEntity)
            }
        }.asLiveData()
}
