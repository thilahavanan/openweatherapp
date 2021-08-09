package com.mobiquity.test.network.dashboard.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mobiquity.test.network.dashboard.datasource.DashboardDataSourceNetworkImpl
import com.mobiquity.test.network.dashboard.models.CurrentWeatherModel
import com.mobiquity.test.network.dashboard.models.WeatherListModel

open class DashboardDelegateImpl(
    private val dataSourceNetwork: DashboardDataSourceNetworkImpl
) : DashboardDelegate {

    override fun getForeCastWeather(
        latitude: String, longitude: String, tempratureUnits: String
    ): LiveData<WeatherListModel> = liveData {
        val data = dataSourceNetwork.getForeCastWeather(
            latitude = latitude,
            longitude = longitude,
            tempratureUnits = tempratureUnits
        )
        emit(data)
    }

    override fun getCurrentWeather(
        latitude: String,
        longitude: String,
        tempratureUnits: String
    ): LiveData<CurrentWeatherModel> = liveData {
        val data = dataSourceNetwork.getCurrentWeather(
            latitude = latitude,
            longitude = longitude,
            tempratureUnits = tempratureUnits
        )
        emit(data)
    }
}