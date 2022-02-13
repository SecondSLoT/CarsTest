package com.secondslot.carstest.presentation.year.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.secondslot.carstest.domain.useCase.LoadYearsUseCase
import javax.inject.Inject

class YearsViewModelFactory @Inject constructor(
    private val loadYearsUseCase: LoadYearsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(YearsViewModel::class.java)) {
            return YearsViewModel(loadYearsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
