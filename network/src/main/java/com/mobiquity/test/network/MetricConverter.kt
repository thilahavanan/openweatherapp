package com.mobiquity.test.openweather

private const val CELCIUS_UNICODE = " \u2103"

fun String.toCelcius(): String = this.plus(CELCIUS_UNICODE)
