package com.wojciszke.diabetesbuddy.adding.if_you_are_recruiter_dont_look_at_this

import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.EditText
import android.widget.NumberPicker

class ThreePickersHack(private val numberPicker1: NumberPicker,
                       private val numberPicker2: NumberPicker,
                       private val numberPicker3: NumberPicker) {

    fun prepareNumberPickers() {
        listOf(numberPicker1, numberPicker2, numberPicker3).forEach {
            it.apply {
                minValue = 0
                maxValue = 9
            }
        }
        val textWatcher1to2 = NumberPickerHacks.focusNextOnInputTextWatcher(numberPicker1, numberPicker2)
        val textWatcher2to3 = NumberPickerHacks.focusNextOnInputTextWatcher(numberPicker2, numberPicker3)
        val textWatcher3toHide = NumberPickerHacks.focusNextOnInputTextWatcher(numberPicker3, null)

        val logInput1 = NumberPickerHacks.findInput(numberPicker1)
        val logInput2 = NumberPickerHacks.findInput(numberPicker2)
        val logInput3 = NumberPickerHacks.findInput(numberPicker3)

        logInput1?.addTextChangedListener(textWatcher1to2)
        logInput2?.addTextChangedListener(textWatcher2to3)
        logInput3?.addTextChangedListener(textWatcher3toHide)

        disableTextWatcherOnTouch(numberPicker1, logInput1, textWatcher1to2)
        disableTextWatcherOnTouch(numberPicker2, logInput2, textWatcher2to3)
        disableTextWatcherOnTouch(numberPicker3, logInput3, textWatcher3toHide)
    }

    fun setGlucoseOnPickers(glucoseInt: Int) {
        if (glucoseInt.toString().length > 3) throw IllegalArgumentException("The glucose level to set on the three pickers cannot be longer than 3 digits")
        val glucoseString = glucoseInt.toString().padStart(3, '0')
        glucoseString.asIterable().zip(listOf(numberPicker1, numberPicker2, numberPicker3)).forEach { (char, numberPicker) ->
            numberPicker.value = char.toString().toInt()  // can't just make char.toInt() 🙃
        }
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