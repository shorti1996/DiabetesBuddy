package com.wojciszke.diabetesbuddy.di.component

import android.content.Context
import com.wojciszke.diabetesbuddy.DiabetesApp
import com.wojciszke.diabetesbuddy.db.LogEntryRepository
import com.wojciszke.diabetesbuddy.di.module.DbModule
import com.wojciszke.diabetesbuddy.model.LogEntryDao
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DbModule::class])
@Singleton
interface AppComponent {
    fun inject(diabetesApp: DiabetesApp)

    val context: Context
    val logEntryDao: LogEntryDao
    val logEntryRepository: LogEntryRepository
}