package com.secondslot.carstest.data.model

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.secondslot.carstest.data.CarsService
import com.secondslot.carstest.domain.model.Make
import javax.inject.Inject

class MakesPagingSource @Inject constructor(
    private val service: CarsService
) : PagingSource<Int, Make>() {

    override fun getRefreshKey(state: PagingState<Int, Make>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Make> {

        val page: Int = params.key ?: CarsService.INITIAL_PAGE_NUMBER
        val pageSize: Int = params.loadSize.coerceAtMost(CarsService.MAX_PAGE_SIZE)

        return try {
            val result = service.loadMakes(page, pageSize)

            if (result.isNotEmpty()) {
                val prevKey = if (page == CarsService.INITIAL_PAGE_NUMBER) null else page - 1
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
    }
}
