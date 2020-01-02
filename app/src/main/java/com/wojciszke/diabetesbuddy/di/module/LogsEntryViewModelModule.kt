package com.wojciszke.diabetesbuddy.di.module

import androidx.lifecycle.ViewModel
import com.wojciszke.diabetesbuddy.logs.LogsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [DbModule::class])
abstract class LogsEntryViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LogsViewModel::class)
    abstract fun bindLogsViewModel(logsViewModel: LogsViewModel): ViewModel
}