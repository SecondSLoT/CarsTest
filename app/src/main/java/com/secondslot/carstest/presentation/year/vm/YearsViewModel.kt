package com.secondslot.carstest.presentation.year.vm

import androidx.lifecycle.ViewModel
import com.secondslot.carstest.domain.model.Year
import com.secondslot.carstest.domain.useCase.LoadYearsUseCase

class YearsViewModel(
    private val loadYearsUseCase: LoadYearsUseCase
) : ViewModel() {

    suspend fun loadYears(makeId: Int, model: String): List<Year> =
        loadYearsUseCase.execute(makeId, model)
}
