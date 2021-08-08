package com.mobiquity.test.network.dashboard.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mobiquity.test.network.dashboard.datasource.DashboardDataSourceNetworkImpl
import com.mobiquity.test.network.dashboard.models.WeatherListModel

open class DashboardDelegateImpl(
    private val dataSourceNetwork: DashboardDataSourceNetworkImpl
) : DashboardDelegate {

    override fun getForeCastWeather(
        latitude: String,
        longitude: String
    ): LiveData<WeatherListModel> = liveData {
        val data = dataSourceNetwork.getForeCastWeather(latitude, longitude)
        emit(data)
    }
}