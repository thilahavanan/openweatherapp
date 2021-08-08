package com.mobiquity.test.network.dashboard.models

data class ForeCastListDataMap(
    val list: List<ForecastDto>? = emptyList(),
    val city: CityWeatherDto? = null
)