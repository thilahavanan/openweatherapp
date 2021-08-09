package com.mobiquity.test.network.dashboard.models

data class WeatherListDto(
    val cod: String? = null,
    val message: String? = null,
    val cnt: Int? = null,
    val list: List<ForecastDto>? = emptyList(),
    val city: CityWeatherDto? = null
)
