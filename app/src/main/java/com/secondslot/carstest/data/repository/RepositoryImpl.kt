package com.secondslot.carstest.data.repository

import androidx.paging.PagingSource
import com.secondslot.carstest.data.CarsService
import com.secondslot.carstest.data.di.ModelsPagingFactory
import com.secondslot.carstest.data.model.MakesPagingSource
import com.secondslot.carstest.domain.Repository
import com.secondslot.carstest.domain.model.Make
import com.secondslot.carstest.domain.model.Model
import com.secondslot.carstest.domain.model.Year
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val makesPagingSource: MakesPagingSource,
    private val modelsPagingFactory: ModelsPagingFactory,
    private val carsService: CarsService
) : Repository {

    override fun loadMakes(): PagingSource<Int, Make> {
        return makesPagingSource
    }

    override fun loadModels(makeId: Int): PagingSource<Int, Model> {
        return modelsPagingFactory.create(makeId)
    }

    override suspend fun loadYears(makeId: Int, model: String): List<Year> {
        return carsService.loadYears(makeId, model)
    }
}
