package com.sample.simpsonsviewer.dagger.componet

import com.sample.simpsonsviewer.dagger.NetworkModule
import com.sample.simpsonsviewer.screens.home.MainListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified MainListViewModel.
     * @param postListViewModel MainListViewModel in which to inject the dependencies
     */
    fun inject(postListViewModel: MainListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}