package com.mobiquity.test.network.dashboard.models

data class CurrentWeatherDto(
    val cod: String? = null,
    val name: String? = null,
    val weather: List<WeatherDto>? = null,
    val main: ForecastMainDto? = null,
    val wind: WindDto? = null,
    val dt: Long? = null
) {
}