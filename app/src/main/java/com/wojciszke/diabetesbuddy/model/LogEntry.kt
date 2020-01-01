package com.wojciszke.diabetesbuddy.model

import org.joda.time.DateTime
import java.lang.Integer.parseInt

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
data class LogEntry(val id: String, val glucose: Int, val date: DateTime, val fasting: Boolean, val note: String)

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