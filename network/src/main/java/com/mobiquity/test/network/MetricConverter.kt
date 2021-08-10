package com.mobiquity.test.openweather

import java.text.SimpleDateFormat
import java.util.*

private const val CELCIUS_UNICODE = " \u2103"

fun String.toCelcius(): String = this.plus(CELCIUS_UNICODE)

fun Date.dateInStringFormat(): String {
    var formatter = SimpleDateFormat("E, MMM dd")
    return formatter.format(this)
}
