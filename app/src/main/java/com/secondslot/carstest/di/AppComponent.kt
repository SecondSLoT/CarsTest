package com.secondslot.carstest.di

import android.content.Context
import com.secondslot.carstest.domain.Repository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {

    fun provideRepository(): Repository

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context
        ): AppComponent
    }
}
