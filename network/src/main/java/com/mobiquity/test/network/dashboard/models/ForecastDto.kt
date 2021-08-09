package com.mobiquity.test.network.dashboard.models

data class ForecastDto(
    val dt: Long? = null,
    val main: ForecastMainDto? = null,
    val weather: List<WeatherDto>? = emptyList()
)

data class ForecastMainDto(
    val temp: Double? = null,
    val feel_like: Double? = null,
    val temp_min: Double? = null,
    val temp_max: Double? = null,
    val pressure: Double? = null,
    val humidity: Double? = null
)