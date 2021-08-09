package com.mobiquity.test.network.dashboard.models

class WindModel(
    val speed: String? = "",
    val deg: Int? = null
) {
    constructor(windDto: WindDto?) : this(
        speed = windDto?.speed.toString() ?: "",
        deg = windDto?.deg ?: 0
    )
}