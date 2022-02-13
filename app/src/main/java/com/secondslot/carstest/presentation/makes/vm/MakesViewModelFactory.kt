package com.secondslot.carstest.presentation.makes.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.secondslot.carstest.domain.useCase.LoadMakesUseCase
import javax.inject.Inject

class MakesViewModelFactory @Inject constructor(
    private val loadMakesUseCase: LoadMakesUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MakesViewModel::class.java)) {
            return MakesViewModel(loadMakesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
