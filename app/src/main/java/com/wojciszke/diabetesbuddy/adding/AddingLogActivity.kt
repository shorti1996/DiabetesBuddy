package com.wojciszke.diabetesbuddy.adding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.wojciszke.diabetesbuddy.DiabetesApp
import com.wojciszke.diabetesbuddy.R
import com.wojciszke.diabetesbuddy.adding.if_you_are_recruiter_dont_look_at_this.ThreePickersHack
import com.wojciszke.diabetesbuddy.boiler.observe
import com.wojciszke.diabetesbuddy.di.component.DaggerLogsEntryComponent
import com.wojciszke.diabetesbuddy.logs.LogsViewModel
import kotlinx.android.synthetic.main.activity_adding_log.*
import javax.inject.Inject

class AddingLogActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val logsViewModel by lazy { ViewModelProviders.of(this, viewModelFactory)[LogsViewModel::class.java] }

    private val threePickersHack by lazy { ThreePickersHack(add_log_number_picker1, add_log_number_picker2, add_log_number_picker3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_log)
        DaggerLogsEntryComponent
                .builder()
                .appComponent((application as DiabetesApp).appComponent)
                .build()
                .inject(this)

        threePickersHack.prepareNumberPickers()

        if (intent.extras?.containsKey(EXTRA_LOG_ID) == true) {
            logsViewModel.singleLog().observe(this) { logEntry ->
                if (logEntry != null) {
                    threePickersHack.setGlucoseOnPickers(logEntry.glucose)
                }
            }
            logsViewModel.setSingleLogId(intent.extras?.getString(EXTRA_LOG_ID)!!)
        }
    }

    companion object {
        const val EXTRA_LOG_ID = "extra-log-id"
    }

}
