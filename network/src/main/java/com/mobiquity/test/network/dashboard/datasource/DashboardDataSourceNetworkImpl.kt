package com.mobiquity.test.network.dashboard.datasource

import com.mobiquity.test.network.basemodels.ModelInfo
import com.mobiquity.test.network.basenetwork.networkApiCall
import com.mobiquity.test.network.dashboard.models.WeatherListModel
import com.mobiquity.test.network.dashboard.network.DashboardRetrofitService
import kotlinx.coroutines.Dispatchers

open class DashboardDataSourceNetworkImpl() : DashboardDataSource {

    override suspend fun getForeCastWeather(latitude: String, longitude: String): WeatherListModel {
        val result = networkApiCall(Dispatchers.IO) {
            DashboardRetrofitService().apiInstance.getForeCastWeather(latitude, longitude)
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
}