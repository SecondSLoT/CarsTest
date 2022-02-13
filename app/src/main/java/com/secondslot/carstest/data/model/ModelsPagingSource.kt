package com.secondslot.carstest.data.model

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.secondslot.carstest.data.CarsService
import com.secondslot.carstest.domain.model.Model
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class ModelsPagingSource @AssistedInject constructor(
    private val service: CarsService,
    @Assisted private val makeId: Int
) : PagingSource<Int, Model>() {

    override fun getRefreshKey(state: PagingState<Int, Model>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Model> {

        val page: Int = params.key ?: INITIAL_PAGE_NUMBER
        val pageSize: Int = params.loadSize.coerceAtMost(MAX_PAGE_SIZE)

        return try {
            val result = service.loadModels(makeId, page, pageSize)

            if (result.isNotEmpty()) {
                val prevKey = if (page == INITIAL_PAGE_NUMBER) null else page - 1
                val nextKey = if (result.size < pageSize) null else page + 1
                LoadResult.Page(result, prevKey, nextKey)
            } else {
                LoadResult.Error(Exception("Error loading data"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error loading data")
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val TAG = "MakePagingSource"

        private const val INITIAL_PAGE_NUMBER = 0
        private const val MAX_PAGE_SIZE = 50
    }
}
