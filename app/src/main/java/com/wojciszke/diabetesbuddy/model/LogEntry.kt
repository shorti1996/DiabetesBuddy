package com.wojciszke.diabetesbuddy.model

import androidx.room.*
import org.joda.time.DateTime
import java.util.*

/**
 * Glucose levels:
 *
 *
 * +===============+===========+=========+============+=======+
 * |               | Excellent |  Good   | Acceptable | Poor  |
 * +===============+===========+=========+============+=======+
 * | Before meal   | 72-109    | 110-144 | 145-180    | >=181 |
 * +---------------+-----------+---------+------------+-------+
 * | 2h after meal | 90-126    | 127-180 | 181-234    | >=235 |
 * +---------------+-----------+---------+------------+-------+
 */
@Entity(tableName = "log_entries")
data class LogEntry(
        @PrimaryKey
        val id: String,
        val glucose: Int,
        val date: DateTime,
        val fasting: Boolean,
        val note: String
) {
    constructor(
            glucose: Int,
            date: DateTime,
            fasting: Boolean,
            note: String
    ) : this(UUID.randomUUID().toString(), glucose, date, fasting, note)
}

fun LogEntry.toGlucoseLevel() = if (fasting) toGlucoseLevelFasting() else toGlucoseLevelMeal()

private fun LogEntry.toGlucoseLevelMeal() = when {
    glucose in 90..126 -> GlucoseLevel.Excellent
    glucose in 127..180 -> GlucoseLevel.Good
    glucose in 181..234 -> GlucoseLevel.Acceptable
    glucose >= 235 -> GlucoseLevel.Poor
    else -> GlucoseLevel.Invalid
}

private fun LogEntry.toGlucoseLevelFasting() = when {
    glucose in 72..109 -> GlucoseLevel.Excellent
    glucose in 110..144 -> GlucoseLevel.Good
    glucose in 145..180 -> GlucoseLevel.Acceptable
    glucose >= 180 -> GlucoseLevel.Poor
    else -> GlucoseLevel.Invalid
}

@Dao
interface LogEntryDao {
    @Query("SELECT * FROM log_entries")
    fun getAll(): List<LogEntry>

    @Query("SELECT * FROM log_entries WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: Array<String>): List<LogEntry>

    /**
     * @param start use [DateTime.toString] ("yyyy-mm-dd")
     * @param end use [DateTime.toString] ("yyyy-mm-dd")
     */
    @Query("SELECT * FROM log_entries WHERE date BETWEEN DATE(:start) AND DATE(:end)")
    fun findBetween(start: DateTime, end: DateTime): LogEntry

    @Insert
    fun insertAll(vararg logEntries: LogEntry)

    @Delete
    fun delete(logEntry: LogEntry)
}