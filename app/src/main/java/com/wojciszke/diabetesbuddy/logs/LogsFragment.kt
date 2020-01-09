package com.wojciszke.diabetesbuddy.logs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wojciszke.diabetesbuddy.DiabetesApp
import com.wojciszke.diabetesbuddy.R
import com.wojciszke.diabetesbuddy.adding.AddingLogActivity
import com.wojciszke.diabetesbuddy.boiler.observe
import com.wojciszke.diabetesbuddy.di.component.DaggerLogsEntryComponent
import com.wojciszke.diabetesbuddy.model.LogEntry
import kotlinx.android.synthetic.main.fragment_logs.*
import javax.inject.Inject


class LogsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val logsViewModel by lazy { ViewModelProviders.of(this, viewModelFactory)[LogsViewModel::class.java] }

    private val logsAdapter by lazy { LogsAdapter { logItem -> startEditForLogItem(logItem) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerLogsEntryComponent
                .builder()
                .appComponent((activity!!.application as DiabetesApp).appComponent)
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_logs, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareLogsRecyclerView(logs_rv)
        logsViewModel.allLogs.observe(this) { logsAdapter.dataSet = it }
    }

    private fun prepareLogsRecyclerView(logsRecyclerView: RecyclerView) = logsRecyclerView.apply {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this@LogsFragment.requireContext())
        adapter = logsAdapter
    }

    private fun startEditForLogItem(logItem: LogEntry) {
        startActivity(Intent(this@LogsFragment.context, AddingLogActivity::class.java).apply { putExtra(AddingLogActivity.EXTRA_LOG_ID, logItem.id) })
    }

    companion object {
        const val TAG = "logs_fragment"

        @JvmStatic
        fun newInstance() = LogsFragment()
    }
}
