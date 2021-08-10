package com.mobiquity.test.openweather.settings.dependency

import com.mobiquity.test.network.DependencyLoader
import org.koin.core.context.loadKoinModules

class SettingsDependenciesloader : DependencyLoader {
    override fun loadModules() {
        loadKoinModules(viewModelModule)
    }
}