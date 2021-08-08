package com.mobiquity.test.network.dashboard.models

class ForeCastModel(
    val dt: Long? = null,
    val main: ForecastMainDto? = null,
    val weather: WeatherDto? = null,
) {
    constructor(forecastDto: ForecastDto?) : this(
        dt = forecastDto?.dt ?: 1L,
        weather = forecastDto?.weather,
        main = forecastDto?.main
    )
}