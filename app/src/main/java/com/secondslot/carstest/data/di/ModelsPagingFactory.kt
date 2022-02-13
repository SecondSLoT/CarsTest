package com.secondslot.carstest.data.di

import com.secondslot.carstest.data.model.ModelsPagingSource
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ModelsPagingFactory {
    fun create(makeId: Int): ModelsPagingSource
}
