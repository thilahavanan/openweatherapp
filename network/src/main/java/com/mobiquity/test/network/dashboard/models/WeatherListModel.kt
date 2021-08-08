package com.mobiquity.test.network.dashboard.models

import com.mobiquity.test.network.basemodels.BaseModel
import com.mobiquity.test.network.basemodels.ErrorModel
import java.util.concurrent.TimeUnit

class WeatherListModel(
    val forecastModel: List<ForeCastModel>? = null,
    val cityModel: CityModel? = null,
    timeoutMins: Long? = TimeUnit.SECONDS.toMinutes(30L),
    errorModel: ErrorModel? = null
) : BaseModel(timeoutMins = timeoutMins, errorModel = errorModel) {
    constructor(
        cod: String?,
        message: String?,
        cnt: Int?,
        list: List<ForecastDto>?,
        city: CityWeatherDto?,
        timeoutMins: Long?,
        errorModel: ErrorModel?
    ) : this(
        timeoutMins = timeoutMins,
        errorModel = errorModel,
        forecastModel = list?.map { ForeCastModel(it) } ?: emptyList(),
        cityModel = CityModel(city)
    )
}