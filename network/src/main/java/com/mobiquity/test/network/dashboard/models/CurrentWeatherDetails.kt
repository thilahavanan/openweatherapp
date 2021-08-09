package com.mobiquity.test.network.dashboard.models

class CurrentWeatherDetails(
    val cod: String? = null,
    val name: String? = null,
    val weather: WeatherDto? = null,
    val main: ForecastMainDto? = null,
    val wind: WindDto? = null,
    val dt: Long? = null
) {
}