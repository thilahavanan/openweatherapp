package com.mobiquity.test.network.dashboard.models

import com.mobiquity.test.openweather.toTimeInString

class ForeCastModel(
    val dateTime: String? = null,
    val main: ForeCastMainModel? = null,
    val weather: List<WeatherModel>? = emptyList()
) {
    constructor(forecastDto: ForecastDto?) : this(
        dateTime = forecastDto?.dt?.toTimeInString() ?: 1L.toTimeInString(),
        weather = forecastDto?.weather?.map { WeatherModel(it) },
        main = forecastDto?.main?.let { ForeCastMainModel(it) }
    )

    fun getTemprature(): String = main?.temp ?: ""
}