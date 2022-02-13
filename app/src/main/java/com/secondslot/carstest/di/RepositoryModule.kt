package com.secondslot.carstest.di

import com.secondslot.carstest.data.repository.RepositoryImpl
import com.secondslot.carstest.domain.Repository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository
}
