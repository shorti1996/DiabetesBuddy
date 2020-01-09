package com.wojciszke.diabetesbuddy.di.component

import com.wojciszke.diabetesbuddy.adding.AddingLogActivity
import com.wojciszke.diabetesbuddy.di.module.LogsEntryViewModelModule
import com.wojciszke.diabetesbuddy.di.module.ViewModelFactoryModule
import com.wojciszke.diabetesbuddy.di.scope.LogsScope
import com.wojciszke.diabetesbuddy.logs.LogsFragment
import dagger.Component

@LogsScope
@Component(dependencies = [AppComponent::class], modules = [ViewModelFactoryModule::class, LogsEntryViewModelModule::class])
interface LogsEntryComponent {
    fun inject(logsFragment: LogsFragment)
    fun inject(addingLogActivity: AddingLogActivity)
}