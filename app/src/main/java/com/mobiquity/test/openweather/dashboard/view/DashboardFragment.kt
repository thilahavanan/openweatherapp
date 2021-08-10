package com.mobiquity.test.openweather.dashboard.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mobiquity.test.network.dashboard.models.ForeCastModel
import com.mobiquity.test.openweather.BaseFragment
import com.mobiquity.test.openweather.R
import com.mobiquity.test.openweather.dashboard.adapter.SegmentedForCastedWeatherAdapter
import com.mobiquity.test.openweather.dashboard.viewmodel.DashboardViewModel
import com.mobiquity.test.openweather.dashboard.viewmodel.TODAY_FORECAST
import com.mobiquity.test.openweather.dashboard.viewmodel.TOMORROW_FORECAST
import com.mobiquity.test.openweather.databinding.DashboardFragmentBinding
import org.koin.android.viewmodel.ext.android.getViewModel
import timber.log.Timber

class DashboardFragment : BaseFragment() {
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: DashboardFragmentBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat: Double = 0.00
    private var lon: Double = 0.00
    private lateinit var segmentedForCastedWeatherAdapter: SegmentedForCastedWeatherAdapter

    @SuppressLint("MissingPermission")
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions: Map<String, Boolean> ->
            permissions.entries.forEach {
                Timber.tag("OnRequest Permission").d("${it.key}-${it.value}")
                fusedLocationClient.lastLocation.addOnSuccessListener { locationObj ->
                    locationObj?.let {
                        lat = it.latitude
                        lon = it.longitude

                        //Forcasted Weather
                        dashboardViewModel.requestForCastedWeather(
                            lat = lat.toString(),
                            lon = lon.toString()
                        )
                        //Current Weather
                        dashboardViewModel.requestCurrentWeather(
                            lat = lat.toString(),
                            lon = lon.toString()
                        )
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Request Permission for Location
        requestPermission.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dashboard_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel = getViewModel<DashboardViewModel>()
        binding.viewModel = dashboardViewModel
        binding.time.format24Hour = null

        dashboardViewModel.segmentedWeatherListDetailsLD.observe(viewLifecycleOwner, Observer {
            initSegmentedForCastWeather(it)
        })

        dashboardViewModel.currentWeatherDetailsLD.observe(viewLifecycleOwner, Observer {

        })

        binding.chipGroup.setOnClickListener {
            when (it.id) {
                R.id.todayForcast -> {
                    dashboardViewModel.setSegmentedForCastList(TODAY_FORECAST)
                }
                R.id.tomorrowForCast -> {
                    dashboardViewModel.setSegmentedForCastList(TOMORROW_FORECAST)
                }
                R.id.thisWeekForcast -> {
                    findNavController().navigate(DashboardFragmentDirections.actionNavigationDashboardToNavigationForcasted())
                }
            }
        }

        binding.todayForcast.setOnClickListener {
            dashboardViewModel.setSegmentedForCastList(TODAY_FORECAST)
        }
        binding.tomorrowForCast.setOnClickListener {
            dashboardViewModel.setSegmentedForCastList(TOMORROW_FORECAST)
        }
        binding.thisWeekForcast.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionNavigationDashboardToNavigationForcasted())
        }
    }

    private fun initSegmentedForCastWeather(forecastList: List<ForeCastModel>) {
        segmentedForCastedWeatherAdapter = SegmentedForCastedWeatherAdapter(
            list = forecastList
        )
        binding.segmentedForecastList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = segmentedForCastedWeatherAdapter
        }
        binding.executePendingBindings()
    }
}