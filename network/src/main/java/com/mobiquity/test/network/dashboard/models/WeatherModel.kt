package com.mobiquity.test.network.dashboard.models

class WeatherModel(
    val id: Int? = null,
    val main: String? = "",
    val description: String? = "",
    val icon: String? = ""
) {
    constructor(weatherDto: WeatherDto?) : this(
        id = weatherDto?.id ?: 0,
        main = weatherDto?.main ?: "",
        description = weatherDto?.description ?: "",
        icon = weatherDto?.icon ?: ""
    )
}