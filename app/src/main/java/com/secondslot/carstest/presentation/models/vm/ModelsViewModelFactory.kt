package com.secondslot.carstest.presentation.models.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.secondslot.carstest.domain.useCase.LoadModelsUseCase
import javax.inject.Inject

class ModelsViewModelFactory @Inject constructor(
    private val loadModelsUseCase: LoadModelsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ModelsViewModel::class.java)) {
            return ModelsViewModel(loadModelsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
