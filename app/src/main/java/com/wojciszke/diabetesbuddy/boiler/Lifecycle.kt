package com.wojciszke.diabetesbuddy.boiler

import androidx.lifecycle.*
import javax.inject.Inject
import javax.inject.Provider

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T) -> Unit) = observe(owner, Observer(observer))

class MultiBindViewModelFactory @Inject constructor(
        private val viewModelsMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModelsMap[modelClass]?.get() as T
            ?: throw IllegalArgumentException("unknown model class $modelClass")

}