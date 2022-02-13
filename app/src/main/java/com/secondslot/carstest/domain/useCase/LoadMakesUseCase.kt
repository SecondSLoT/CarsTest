package com.secondslot.carstest.domain.useCase

import androidx.paging.PagingSource
import com.secondslot.carstest.domain.Repository
import com.secondslot.carstest.domain.model.Make
import javax.inject.Inject

class LoadMakesUseCase @Inject constructor(
    private val repository: Repository
) {

    fun execute(): PagingSource<Int, Make> = repository.loadMakes()
}
