package com.wojciszke.diabetesbuddy.boiler

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(block: FragmentTransaction.() -> Unit) =
    with(beginTransaction()) {
        block()
        commit()
    }
