package com.mobiquity.test.openweather.settings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobiquity.test.openweather.BaseFragment
import com.mobiquity.test.openweather.R
import com.mobiquity.test.openweather.settings.viewmodel.SettingsViewModel
import org.koin.android.viewmodel.ext.android.getViewModel

class SettingsFragment : BaseFragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel = getViewModel<SettingsViewModel>()
        val root = inflater.inflate(R.layout.settings_fragment, container, false)
        return root
    }
}