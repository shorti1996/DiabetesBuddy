package com.wojciszke.diabetesbuddy.logs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.wojciszke.diabetesbuddy.R
import com.wojciszke.diabetesbuddy.model.LogEntry
import kotlinx.android.synthetic.main.fragment_logs.*
import org.joda.time.DateTime

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LogsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LogsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_logs, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logs_rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@LogsFragment.requireContext())
            adapter = LogsAdapter(dataset)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        const val TAG = "logs_fragment"

        val dataset = listOf(
            LogEntry("1", 80, DateTime.now(), false, "Notatka"),
            LogEntry("2", 142, DateTime.now().minusMinutes(10), true, ""),
            LogEntry("3", 181, DateTime.now().minusDays(1).minusMinutes(21), true, "Notatka 123"),
            LogEntry("4", 164, DateTime.now().minusDays(1).minusHours(2).minusMinutes(38), false, "Test"),
            LogEntry("5", 94, DateTime.now().minusDays(2).minusHours(8).minusMinutes(23), false, "Test 2")
        )

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LogsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LogsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
