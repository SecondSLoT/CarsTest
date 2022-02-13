package com.secondslot.carstest.presentation.year.di

import androidx.lifecycle.ViewModelProvider
import com.secondslot.carstest.presentation.year.vm.YearsViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface YearsModule {

    @YearsScope
    @Binds
    fun bindYearsViewModelFactory(
        impl: YearsViewModelFactory
    ): ViewModelProvider.Factory
}
