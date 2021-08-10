package com.mobiquity.test.openweather.settings.dependency

import com.mobiquity.test.openweather.settings.viewmodel.SettingsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel(override = true) {
        SettingsViewModel()
    }
}