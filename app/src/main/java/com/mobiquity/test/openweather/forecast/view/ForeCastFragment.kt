package com.mobiquity.test.openweather.forecast.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mobiquity.test.openweather.BaseFragment
import com.mobiquity.test.openweather.R
import com.mobiquity.test.openweather.dashboard.viewmodel.DashboardViewModel
import com.mobiquity.test.openweather.databinding.ForecastFragmentBinding
import org.koin.android.viewmodel.ext.android.getViewModel

class ForeCastFragment : BaseFragment() {
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: ForecastFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.forecast_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel = getViewModel<DashboardViewModel>()
    }
}