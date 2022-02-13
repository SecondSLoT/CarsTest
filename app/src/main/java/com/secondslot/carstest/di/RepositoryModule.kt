package com.secondslot.carstest.di

import com.secondslot.carstest.data.repository.RepositoryImpl
import com.secondslot.carstest.domain.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository
}
