package com.wojciszke.diabetesbuddy.di.component

import com.wojciszke.diabetesbuddy.di.FragmentScope
import com.wojciszke.diabetesbuddy.di.module.LogsEntryViewModelModule
import com.wojciszke.diabetesbuddy.di.module.ViewModelFactoryModule
import com.wojciszke.diabetesbuddy.logs.LogsFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [ViewModelFactoryModule::class, LogsEntryViewModelModule::class])
interface LogsEntryComponent {
    fun inject(logsFragment: LogsFragment)
}