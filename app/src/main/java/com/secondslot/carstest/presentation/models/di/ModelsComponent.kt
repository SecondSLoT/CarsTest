package com.secondslot.carstest.presentation.models.di

import com.secondslot.carstest.di.AppComponent
import com.secondslot.carstest.presentation.makes.ui.MakesFragment
import com.secondslot.carstest.presentation.models.ui.ModelsFragment
import dagger.Component

@ModelsScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ModelsModule::class]
)
interface ModelsComponent {

    fun inject(modelsFragment: ModelsFragment)

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent
        ): ModelsComponent
    }
}
