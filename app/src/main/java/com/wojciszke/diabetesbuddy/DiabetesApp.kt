package com.wojciszke.diabetesbuddy

import android.app.Application
import androidx.room.Room
import com.wojciszke.diabetesbuddy.model.AppDatabase

class DiabetesApp : Application() {
    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app-database"
        ).build()
    }
}