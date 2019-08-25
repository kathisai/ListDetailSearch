package com.sample.simpsonsviewer.base

import androidx.lifecycle.ViewModel
import com.sample.simpsonsviewer.dagger.NetworkModule
import com.sample.simpsonsviewer.dagger.componet.DaggerViewModelInjector
import com.sample.simpsonsviewer.dagger.componet.ViewModelInjector
import com.sample.simpsonsviewer.screens.home.MainListViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainListViewModel -> injector.inject(this)
        }
    }
}