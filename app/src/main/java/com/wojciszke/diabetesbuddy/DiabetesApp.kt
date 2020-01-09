package com.wojciszke.diabetesbuddy

import android.app.Application
import com.wojciszke.diabetesbuddy.boiler.launchIO
import com.wojciszke.diabetesbuddy.db.AppDatabase
import com.wojciszke.diabetesbuddy.di.component.AppComponent
import com.wojciszke.diabetesbuddy.di.component.DaggerAppComponent
import com.wojciszke.diabetesbuddy.di.module.DbModule
import com.wojciszke.diabetesbuddy.model.LogEntry
import org.joda.time.DateTime
import javax.inject.Inject

class DiabetesApp : Application() {
    val appComponent: AppComponent by lazy { DaggerAppComponent.builder().dbModule(DbModule(this)).build() }
    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

        appDatabase.run {
            launchIO {
                clearAllTables()
                logEntryDao().insertAll(*dataset.toTypedArray())
            }
        }
    }

    companion object {
        private val dataset = listOf(
                LogEntry(80, DateTime.now(), false, "Notatka"),
                LogEntry(142, DateTime.now().minusMinutes(10), true, ""),
                LogEntry(181, DateTime.now().minusDays(1).minusMinutes(21), true, "Notatka 123"),
                LogEntry(164, DateTime.now().minusDays(1).minusHours(2).minusMinutes(38), false, "Test"),
                LogEntry(94, DateTime.now().minusDays(2).minusHours(8).minusMinutes(23), false, "Test 2")
        )
    }
}
