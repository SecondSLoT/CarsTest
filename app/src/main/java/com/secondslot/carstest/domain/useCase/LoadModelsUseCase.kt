package com.secondslot.carstest.domain.useCase

import androidx.paging.PagingSource
import com.secondslot.carstest.domain.Repository
import com.secondslot.carstest.domain.model.Model
import javax.inject.Inject

class LoadModelsUseCase @Inject constructor(
    private val repository: Repository
) {

    fun execute(makeId: Int): PagingSource<Int, Model> = repository.loadModels(makeId)
}
