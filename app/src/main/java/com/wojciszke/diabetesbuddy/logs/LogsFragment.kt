package com.wojciszke.diabetesbuddy.logs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wojciszke.diabetesbuddy.DiabetesApp
import com.wojciszke.diabetesbuddy.MainActivity
import com.wojciszke.diabetesbuddy.R
import com.wojciszke.diabetesbuddy.boiler.observe
import com.wojciszke.diabetesbuddy.di.component.DaggerLogsEntryComponent
import kotlinx.android.synthetic.main.fragment_logs.*
import javax.inject.Inject


/*// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"*/

class LogsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val logsViewModel by lazy { ViewModelProviders.of(this, viewModelFactory)[LogsViewModel::class.java] }

/*    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerLogsEntryComponent
                .builder()
                .appComponent((activity!!.application as DiabetesApp).appComponent)
                .build()
                .inject(this)

/*        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_logs, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareLogsRecyclerView(logs_rv)
        logsViewModel.allLogs.observe(this) {
            it.forEach { logEntry -> Log.d(TAG, "allLogs: $logEntry") }
        }
    }

    private fun prepareLogsRecyclerView(logsRecyclerView: RecyclerView) = logsRecyclerView.apply {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this@LogsFragment.requireContext())
        adapter = LogsAdapter(MainActivity.dataset)
    }

    companion object {

        const val TAG = "logs_fragment"

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(/*param1: String, param2: String*/) =
                LogsFragment().apply {
                    /*
                                    arguments = Bundle().apply {
                                        putString(ARG_PARAM1, param1)
                                        putString(ARG_PARAM2, param2)
                                    }
                    */
                }
    }
}
