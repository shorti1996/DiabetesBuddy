package com.wojciszke.diabetesbuddy.db

import androidx.lifecycle.LiveData
import com.wojciszke.diabetesbuddy.model.LogEntry
import com.wojciszke.diabetesbuddy.model.LogEntryDao
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogEntryRepository @Inject constructor(private val logEntryDao: LogEntryDao) {
    fun getAll(): LiveData<List<LogEntry>> = logEntryDao.getAll()

    fun loadAllByIds(userIds: Array<String>): LiveData<List<LogEntry>> = logEntryDao.loadAllByIds(userIds)

    fun findBetween(start: DateTime, end: DateTime): LiveData<LogEntry> =
            logEntryDao.findBetween(start.toString(LogEntryDao.DATETIME_SERIALIZATION_STRING),
                    end.toString(LogEntryDao.DATETIME_SERIALIZATION_STRING))

    fun insertAll(vararg logEntries: LogEntry) = logEntryDao.insertAll(*logEntries)

    fun delete(logEntry: LogEntry) = logEntryDao.delete(logEntry)
}