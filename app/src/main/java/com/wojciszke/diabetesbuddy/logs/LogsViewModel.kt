package com.wojciszke.diabetesbuddy.logs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wojciszke.diabetesbuddy.db.LogEntryRepository
import javax.inject.Inject

class LogsViewModel @Inject constructor(private val logEntryRepository: LogEntryRepository) : ViewModel() {
    val allLogs by lazy { logEntryRepository.getAll() }

    private val singleLogId = MutableLiveData<String>()
    fun singleLog() = Transformations.switchMap(singleLogId) {
        logEntryRepository.getById(it)
    }

    fun setSingleLogId(logId: String) {
        singleLogId.value = logId
    }
}

