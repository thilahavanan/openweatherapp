package com.mobiquity.test.openweather.dashboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobiquity.test.openweather.BaseFragment
import com.mobiquity.test.openweather.R
import com.mobiquity.test.openweather.dashboard.viewmodel.DashboardViewModel
import org.koin.android.viewmodel.ext.android.getViewModel

class DashboardFragment : BaseFragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = getViewModel<DashboardViewModel>()
        val root = inflater.inflate(R.layout.dashboard_fragment, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel.requestWeather()
    }
}