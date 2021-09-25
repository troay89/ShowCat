package com.example.showcat.ui.firstscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.showcat.domain.model.CatEntity
import com.example.showcat.domain.usecase.GetCatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    getCatsUseCase: GetCatsUseCase
) : ViewModel() {
    val photos: LiveData<PagingData<CatEntity>> = getCatsUseCase.execute().cachedIn(viewModelScope)
}
