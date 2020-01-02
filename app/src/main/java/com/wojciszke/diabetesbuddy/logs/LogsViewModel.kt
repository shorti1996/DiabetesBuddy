package com.wojciszke.diabetesbuddy.logs

import androidx.lifecycle.ViewModel
import com.wojciszke.diabetesbuddy.db.LogEntryRepository
import javax.inject.Inject

class LogsViewModel @Inject constructor(logEntryRepository: LogEntryRepository) : ViewModel() {
    val allLogs by lazy { logEntryRepository.getAll() }
}

