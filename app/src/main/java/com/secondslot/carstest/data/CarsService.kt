package com.secondslot.carstest.data

import com.secondslot.carstest.data.db.AppDatabase
import com.secondslot.carstest.domain.model.Make
import com.secondslot.carstest.domain.model.Model
import com.secondslot.carstest.domain.model.Year
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarsService @Inject constructor(
    private val database: AppDatabase
) {

    private var makes: MutableList<Make> = mutableListOf()
    private var currentMakeId: Int? = null
    private var models: MutableList<Model> = mutableListOf()

    suspend fun loadMakes(page: Int, pageSize: Int): List<Make> {
        if (makes.isEmpty()) makes = database.carDao.getMakes().toMutableList()

        val startIndex = if (calculateStartIndex(page, pageSize) <= makes.lastIndex) {
            calculateStartIndex(page, pageSize)
        } else {
            return emptyList()
        }

        val endIndex = if (startIndex + pageSize <= makes.lastIndex) {
            startIndex + pageSize
        } else {
            makes.lastIndex
        }

        return makes.subList(startIndex, endIndex)
    }

    suspend fun loadModels(makeId: Int, page: Int, pageSize: Int): List<Model> {
        if (models.isEmpty() || currentMakeId != makeId) {
            models = database.carDao.getModels(makeId).toMutableList()
            currentMakeId = makeId
        }

        val startIndex = if (calculateStartIndex(page, pageSize) <= models.lastIndex) {
            calculateStartIndex(page, pageSize)
        } else {
            return emptyList()
        }

        val endIndex = if (startIndex + pageSize <= models.lastIndex) {
            startIndex + pageSize
        } else {
            models.lastIndex
        }

        return models.subList(startIndex, endIndex)
    }

    suspend fun loadYears(makeId: Int, model: String): List<Year> {
        return database.carDao.getYears(makeId, model)
    }

    private fun calculateStartIndex(page: Int, pageSize: Int): Int {
        if (page == 0) return 0
        return page * pageSize - 1
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 0
        const val MAX_PAGE_SIZE = 50
    }
}
