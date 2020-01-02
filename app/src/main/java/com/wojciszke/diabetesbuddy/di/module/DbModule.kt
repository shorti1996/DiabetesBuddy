package com.wojciszke.diabetesbuddy.di.module

import android.content.Context
import androidx.room.Room
import com.wojciszke.diabetesbuddy.db.AppDatabase
import com.wojciszke.diabetesbuddy.model.LogEntryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DbModule(val context: Context) {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java, AppDatabase.DATABASE_NAME
        )
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideLogEntryDao(appDatabase: AppDatabase): LogEntryDao {
        return appDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideContext(): Context = context
}