package com.mobiquity.test.openweather.settings.dependency

import com.mobiquity.test.network.DependencyLoader

class SettingsDependenciesloader : DependencyLoader {
    override fun loadModules() {
        viewModelModule
    }
}