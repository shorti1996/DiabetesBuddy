package com.wojciszke.diabetesbuddy.boiler

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun launchIO(context: CoroutineContext = EmptyCoroutineContext,
             start: CoroutineStart = CoroutineStart.DEFAULT,
             block: suspend CoroutineScope.() -> Unit) = CoroutineScope(Dispatchers.IO).launch(context, start, block)