package com.secondslot.carstest.presentation.year.di

import com.secondslot.carstest.di.AppComponent
import com.secondslot.carstest.presentation.year.ui.YearsFragment
import dagger.Component

@YearsScope
@Component(
    dependencies = [AppComponent::class],
    modules = [YearsModule::class]
)
interface YearsComponent {

    fun inject(yearsFragment: YearsFragment)

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent
        ): YearsComponent
    }
}
