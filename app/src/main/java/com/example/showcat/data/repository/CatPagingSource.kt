package com.example.showcat.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.showcat.data.api.retrofit.CatApiService
import com.example.showcat.data.repository.mapper.ApiToEntityMapper
import com.example.showcat.domain.model.CatEntity

class CatPagingSource(
    private val catApiService: CatApiService,
): PagingSource<Int, CatEntity>() {
    override fun getRefreshKey(state: PagingState<Int, CatEntity>): Int {
        return CAT_STARTING_PAGE_INDEX
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatEntity> {
        val position = params.key?: CAT_STARTING_PAGE_INDEX

        return try {
            val photos: List<CatEntity> = ApiToEntityMapper.map(catApiService.getPhoto(params.loadSize, position))
            Log.d("CatPagingSource", photos.size.toString())
            LoadResult.Page(
                data = photos,
                prevKey = if(position == CAT_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if(photos.isEmpty()) null else position + 1
            )
        }catch (exception: Exception){
            LoadResult.Error(exception)
        }
    }

    companion object {
        private const val CAT_STARTING_PAGE_INDEX = 1
    }
}
