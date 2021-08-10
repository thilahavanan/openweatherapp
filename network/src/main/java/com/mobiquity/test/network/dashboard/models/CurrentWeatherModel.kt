package com.mobiquity.test.network.dashboard.models

import com.mobiquity.test.network.basemodels.BaseModel
import com.mobiquity.test.network.basemodels.ErrorModel
import java.util.concurrent.TimeUnit

class CurrentWeatherModel(
    val mainModel: ForeCastMainModel? = null,
    val windModel: WindModel? = null,
    val weatherModel: List<WeatherModel>? = null,
    val cityName: String? = "",
    timeoutMins: Long? = TimeUnit.SECONDS.toMinutes(30L),
    errorModel: ErrorModel? = null
) : BaseModel(timeoutMins = timeoutMins, errorModel = errorModel) {
    constructor(
        name: String?,
        weather: List<WeatherDto>?,
        main: ForecastMainDto?,
        wind: WindDto?,
        dt: Long?,
        timeoutMins: Long?,
        errorModel: ErrorModel?
    ) : this(
        timeoutMins = timeoutMins,
        errorModel = errorModel,
        mainModel = ForeCastMainModel(main),
        windModel = WindModel(wind),
        weatherModel = weather?.map { WeatherModel(it) },
        cityName = name
    )

    fun getTodayWeatherCondition(): String = weatherModel?.first()?.description ?: ""
}