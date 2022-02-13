package com.secondslot.carstest.domain

import androidx.paging.PagingSource
import com.secondslot.carstest.domain.model.Make
import com.secondslot.carstest.domain.model.Model
import com.secondslot.carstest.domain.model.Year

interface Repository {

    fun loadMakes(): PagingSource<Int, Make>

    fun loadModels(makeId: Int): PagingSource<Int, Model>

    suspend fun loadYears(makeId: Int, model: String): List<Year>
}
