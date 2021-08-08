package com.mobiquity.test.network.dashboard.models

class CityModel(
    val id: Int = 0,
    val country: String = "",
    val name: String = "",
) {
    constructor(cityWeatherDto: CityWeatherDto?) : this(
        id = cityWeatherDto?.id ?: 0,
        country = cityWeatherDto?.country ?: "",
        name = cityWeatherDto?.name ?: ""
    )
}