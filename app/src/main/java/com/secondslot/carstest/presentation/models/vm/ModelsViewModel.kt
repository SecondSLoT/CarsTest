package com.secondslot.carstest.presentation.models.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.secondslot.carstest.domain.model.Model
import com.secondslot.carstest.domain.useCase.LoadModelsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ModelsViewModel(
    private val loadModelsUseCase: LoadModelsUseCase
) : ViewModel() {

    fun loadModels(makeId: Int): StateFlow<PagingData<Model>> =
        Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            loadModelsUseCase.execute(makeId)
        }
            .flow
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    companion object {
        private const val PAGE_SIZE = 15
    }
}
