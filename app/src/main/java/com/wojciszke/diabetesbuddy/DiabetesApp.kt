package com.wojciszke.diabetesbuddy

import android.app.Application
import com.wojciszke.diabetesbuddy.db.AppDatabase
import com.wojciszke.diabetesbuddy.di.component.AppComponent
import com.wojciszke.diabetesbuddy.di.component.DaggerAppComponent
import com.wojciszke.diabetesbuddy.di.module.DbModule
import javax.inject.Inject

class DiabetesApp : Application() {
    val appComponent: AppComponent by lazy { DaggerAppComponent.builder().dbModule(DbModule(this)).build() }
    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}