package com.wojciszke.diabetesbuddy.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.wojciszke.diabetesbuddy.model.LogEntry
import com.wojciszke.diabetesbuddy.model.LogEntryDao
import org.joda.time.DateTime

@Database(entities = [LogEntry::class], version = 1)
@TypeConverters(DiabetesAppTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun logEntryDao(): LogEntryDao

    companion object {
        const val DATABASE_NAME = "app-database"
    }
}

class DiabetesAppTypeConverters {
    @TypeConverter
    fun fromDateTimeToString(dateTime: DateTime): String = dateTime.toString()

    @TypeConverter
    fun fromStringToDateTime(dateTimeString: String): DateTime = DateTime.parse(dateTimeString)
}
