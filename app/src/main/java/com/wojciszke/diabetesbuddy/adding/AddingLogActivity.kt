package com.wojciszke.diabetesbuddy.adding

import android.os.Bundle
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.EditText
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import com.wojciszke.diabetesbuddy.R
import kotlinx.android.synthetic.main.activity_adding_log.*

class AddingLogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_log)

        prepareNumberPickers()
    }

    private fun prepareNumberPickers() {
        listOf(add_log_number_picker1, add_log_number_picker2, add_log_number_picker3).forEach {
            it.apply {
                minValue = 0
                maxValue = 9
            }
        }
        val textWatcher1to2 = NumberPickerHacks.focusNextOnInputTextWatcher(add_log_number_picker1, add_log_number_picker2)
        val textWatcher2to3 = NumberPickerHacks.focusNextOnInputTextWatcher(add_log_number_picker2, add_log_number_picker3)
        val textWatcher3toHide = NumberPickerHacks.focusNextOnInputTextWatcher(add_log_number_picker3, null)

        val logInput1 = NumberPickerHacks.findInput(add_log_number_picker1)
        val logInput2 = NumberPickerHacks.findInput(add_log_number_picker2)
        val logInput3 = NumberPickerHacks.findInput(add_log_number_picker3)

        logInput1?.addTextChangedListener(textWatcher1to2)
        logInput2?.addTextChangedListener(textWatcher2to3)
        logInput3?.addTextChangedListener(textWatcher3toHide)

        disableTextWatcherOnTouch(add_log_number_picker1, logInput1, textWatcher1to2)
        disableTextWatcherOnTouch(add_log_number_picker2, logInput2, textWatcher2to3)
        disableTextWatcherOnTouch(add_log_number_picker3, logInput3, textWatcher3toHide)
    }

    private fun disableTextWatcherOnTouch(numberPicker: NumberPicker, logInput1: EditText?, textWatcher1to2: TextWatcher) {
        numberPicker.setOnTouchListener { _, event ->
            when (event!!.action) {
                MotionEvent.ACTION_DOWN -> logInput1?.removeTextChangedListener(textWatcher1to2)
                MotionEvent.ACTION_UP -> logInput1?.addTextChangedListener(textWatcher1to2)
            }
            false
        }
    }

}
