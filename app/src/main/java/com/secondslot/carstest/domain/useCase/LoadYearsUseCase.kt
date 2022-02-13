package com.secondslot.carstest.domain.useCase

import com.secondslot.carstest.domain.Repository
import com.secondslot.carstest.domain.model.Year
import javax.inject.Inject

class LoadYearsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun execute(makeId: Int, model: String): List<Year> =
        repository.loadYears(makeId, model)
}
