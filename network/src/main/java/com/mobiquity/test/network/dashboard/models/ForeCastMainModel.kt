package com.mobiquity.test.network.dashboard.models

import com.mobiquity.test.openweather.toCelcius

class ForeCastMainModel(
    val temp: String? = "",
    val feel_like: String? = "",
    val humidity: String? = "",
    val maxTemp: String? = "",
    val minTemp: String? = ""
) {
    constructor(forecastMainDto: ForecastMainDto?) : this(
        temp = forecastMainDto?.temp.toString().toCelcius(),
        feel_like = forecastMainDto?.feel_like.toString(),
        humidity = forecastMainDto?.humidity.toString(),
        maxTemp = forecastMainDto?.temp_max.toString().toCelcius(),
        minTemp = forecastMainDto?.temp_min.toString().toCelcius()
    )
}