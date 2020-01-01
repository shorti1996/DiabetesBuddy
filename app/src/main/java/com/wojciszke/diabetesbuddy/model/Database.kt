package com.wojciszke.diabetesbuddy.model


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import org.joda.time.DateTime

@Database(entities = [LogEntry::class], version = 1)
@TypeConverters(DiabetesAppTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): LogEntryDao
}

class DiabetesAppTypeConverters {
    @TypeConverter
    fun fromDateTimeToString(dateTime: DateTime): String = dateTime.toString()

    @TypeConverter
    fun fromStringToDateTime(dateTimeString: String): DateTime = DateTime.parse(dateTimeString)
}
