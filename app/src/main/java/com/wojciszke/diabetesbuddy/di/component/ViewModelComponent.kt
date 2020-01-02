package com.wojciszke.diabetesbuddy.di.component

import com.wojciszke.diabetesbuddy.di.module.LogsEntryViewModelModule
import com.wojciszke.diabetesbuddy.di.module.ViewModelFactoryModule
import dagger.Component


@Component(modules = [ViewModelFactoryModule::class, LogsEntryViewModelModule::class])
interface ViewModelComponent