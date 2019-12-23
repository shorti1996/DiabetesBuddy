package com.wojciszke.diabetesbuddy.adding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wojciszke.diabetesbuddy.R
import kotlinx.android.synthetic.main.activity_adding_log.*

class AddingLogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_log)


        listOf(add_log_number_picker1, add_log_number_picker2, add_log_number_picker3).forEach {
            it.apply {
                minValue = 0
                maxValue = 9
            }
        }
        add_log_number_picker1.setOnValueChangedListener { picker, oldVal, newVal ->

        }
    }

}
