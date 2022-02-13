package com.secondslot.carstest.presentation.models.di

import androidx.lifecycle.ViewModelProvider
import com.secondslot.carstest.presentation.models.vm.ModelsViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ModelsModule {

    @ModelsScope
    @Binds
    fun bindModelsViewModelFactory(
        impl: ModelsViewModelFactory
    ): ViewModelProvider.Factory
}
