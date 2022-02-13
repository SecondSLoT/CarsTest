package com.secondslot.carstest.presentation.makes.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.secondslot.carstest.domain.model.Make
import com.secondslot.carstest.domain.useCase.LoadMakesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MakesViewModel(
    private val loadMakesUseCase: LoadMakesUseCase
) : ViewModel() {

    val makes: StateFlow<PagingData<Make>> = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        loadMakesUseCase.execute()
    }
        .flow
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    companion object {
        private const val PAGE_SIZE = 15
    }
}
