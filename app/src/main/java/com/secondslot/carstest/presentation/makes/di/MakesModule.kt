package com.secondslot.carstest.presentation.makes.di

import androidx.lifecycle.ViewModelProvider
import com.secondslot.carstest.presentation.makes.vm.MakesViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface MakesModule {

    @MakesScope
    @Binds
    fun bindMakesViewModelFactory(
        impl: MakesViewModelFactory
    ): ViewModelProvider.Factory
}
