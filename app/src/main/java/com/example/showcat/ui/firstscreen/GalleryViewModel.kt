package com.example.showcat.ui.firstscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.showcat.data.api.model.CatApi
import com.example.showcat.data.repository.CatRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repositoryImpl: CatRepositoryImpl
) : ViewModel() {

    val photos: LiveData<PagingData<CatApi>> = repositoryImpl.getResults().cachedIn(viewModelScope)
}