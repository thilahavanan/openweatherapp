package com.mobiquity.test.openweather

import java.text.SimpleDateFormat
import java.util.*

private const val CELCIUS_UNICODE = " \u2103"

fun String.toCelcius(): String = this.plus(CELCIUS_UNICODE)

fun Date.dateInStringFormat(): String {
    val formatter = SimpleDateFormat("E, MMM dd", Locale.getDefault())
    return formatter.format(this)
}

fun Long.toTimeInString(): String {
    val date = Date(this * 1000)
    val formatter = SimpleDateFormat("hh a", Locale.getDefault())
    return formatter.format(date)
}
