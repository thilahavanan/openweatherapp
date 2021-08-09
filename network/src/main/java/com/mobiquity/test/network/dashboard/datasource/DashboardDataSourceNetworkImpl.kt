package com.mobiquity.test.network.dashboard.datasource

import com.mobiquity.test.network.basemodels.ModelInfo
import com.mobiquity.test.network.basenetwork.networkApiCall
import com.mobiquity.test.network.dashboard.models.CurrentWeatherModel
import com.mobiquity.test.network.dashboard.models.WeatherListModel
import com.mobiquity.test.network.dashboard.network.DashboardRetrofitService
import kotlinx.coroutines.Dispatchers

open class DashboardDataSourceNetworkImpl() : DashboardDataSource {

    override suspend fun getForeCastWeather(
        latitude: String,
        longitude: String,
        tempratureUnits: String
    ): WeatherListModel {
        val result = networkApiCall(Dispatchers.IO) {
            DashboardRetrofitService().apiInstance.getForeCastWeather(
                latitude = latitude, longitude = longitude, tempratureUnits = tempratureUnits
            )
        }
        val modelInfo = ModelInfo(result = result)
        return WeatherListModel(
            list = modelInfo.responseBody?.list,
            city = modelInfo.responseBody?.city,
            cnt = modelInfo.responseBody?.cnt,
            cod = modelInfo.responseBody?.cod,
            message = modelInfo.responseBody?.message,
            errorModel = modelInfo.errorModel,
            timeoutMins = modelInfo.timeoutMins
        )
    }

    override suspend fun getCurrentWeather(
        latitude: String,
        longitude: String,
        tempratureUnits: String
    ): CurrentWeatherModel {
        val result = networkApiCall(Dispatchers.IO) {
            DashboardRetrofitService().apiInstance.getCurrentWeather(
                latitude = latitude, longitude = longitude, tempratureUnits = tempratureUnits
            )
        }
        val modelInfo = ModelInfo(result = result)
        return CurrentWeatherModel(
            name = modelInfo.responseBody?.name,
            weather = modelInfo.responseBody?.weather,
            wind = modelInfo.responseBody?.wind,
            main = modelInfo.responseBody?.main,
            dt = modelInfo.responseBody?.dt,
            errorModel = modelInfo.errorModel,
            timeoutMins = modelInfo.timeoutMins
        )
    }
}