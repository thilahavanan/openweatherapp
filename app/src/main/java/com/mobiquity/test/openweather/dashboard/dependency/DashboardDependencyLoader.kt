package com.mobiquity.test.openweather.dashboard.dependency

import com.mobiquity.test.network.DependencyLoader
import org.koin.core.context.loadKoinModules

class DashboardDependencyLoader : DependencyLoader {
    override fun loadModules() {
        loadKoinModules(
            viewModelModule, networkModule, usecaseModule,
            repositoryModule
        )
    }
}