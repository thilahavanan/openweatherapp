package com.mobiquity.test.network.dashboard.models

class ForeCastModel(
    val dt: Long? = null,
    val main: ForeCastMainModel? = null,
    val weather: List<WeatherModel>? = emptyList(),
) {
    constructor(forecastDto: ForecastDto?) : this(
        dt = forecastDto?.dt ?: 1L,
        weather = forecastDto?.weather?.map { WeatherModel(it) },
        main = forecastDto?.main?.let { ForeCastMainModel(it) }
    )
}