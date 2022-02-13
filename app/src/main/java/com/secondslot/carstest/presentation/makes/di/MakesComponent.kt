package com.secondslot.carstest.presentation.makes.di

import com.secondslot.carstest.di.AppComponent
import com.secondslot.carstest.presentation.makes.ui.MakesFragment
import dagger.Component

@MakesScope
@Component(
    dependencies = [AppComponent::class],
    modules = [MakesModule::class]
)
interface MakesComponent {

    fun inject(makesFragment: MakesFragment)

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent
        ): MakesComponent
    }
}
