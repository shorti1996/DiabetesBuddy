package com.wojciszke.diabetesbuddy.logs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import com.wojciszke.diabetesbuddy.R
import com.wojciszke.diabetesbuddy.model.GlucoseLevel
import com.wojciszke.diabetesbuddy.model.LogEntry
import com.wojciszke.diabetesbuddy.model.toGlucoseLevel
import kotlinx.android.synthetic.main.log_entry_vh.view.*
import org.joda.time.format.DateTimeFormat
import java.util.*

class LogsAdapter(dataSet: List<LogEntry>) : RecyclerView.Adapter<LogsAdapter.LogsViewHolder>() {
    var dataSet = dataSet
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder =
        LogsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.log_entry_vh, parent, false))

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) = with(holder.itemView) {
        val item = dataSet[position]
        measurement_value.text = item.glucose.toString()
        date.text = item.date.toString(DateTimeFormat.patternForStyle("S-", Locale.getDefault()))
        time.text = item.date.toString(DateTimeFormat.patternForStyle("-S", Locale.getDefault()))
        fasting_tv.text = if (item.fasting) context.getString(R.string.fasting_status_true) else context.getString(R.string.fasting_status_false)
        item.toGlucoseLevel().toColorRes()?.let { colorRes ->
            val color = context.getColor(colorRes)
            measurement_separator.setBackgroundColor(color)
//            log_card.setCardBackgroundColor(ColorUtils.setAlphaComponent(color, context.resources.getInteger(R.integer.card_log_background_alpha)))
        }
        Unit
    }

    inner class LogsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

@ColorRes
private fun GlucoseLevel.toColorRes() = when (this) {
    GlucoseLevel.Excellent -> R.color.glucose_level_excellent
    GlucoseLevel.Good -> R.color.glucose_level_good
    GlucoseLevel.Acceptable -> R.color.glucose_level_acceptable
    GlucoseLevel.Poor -> R.color.glucose_level_poor
    GlucoseLevel.Invalid -> null
}
