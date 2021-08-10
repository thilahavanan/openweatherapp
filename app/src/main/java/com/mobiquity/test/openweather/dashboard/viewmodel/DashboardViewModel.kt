package com.mobiquity.test.openweather.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mobiquity.test.network.basemodels.ErrorModel
import com.mobiquity.test.network.dashboard.models.CurrentWeatherModel
import com.mobiquity.test.network.dashboard.models.ForeCastModel
import com.mobiquity.test.network.dashboard.models.WeatherListModel
import com.mobiquity.test.openweather.dashboard.usecase.DashboardUseCase
import com.mobiquity.test.openweather.dateInStringFormat
import java.util.*

const val TEMPRATURE_UNITS = "metric"
const val TODAY_FORECAST = 1
const val TOMORROW_FORECAST = 2

class DashboardViewModel(
    private val useCase: DashboardUseCase
) : ViewModel() {
    private var weatherListResponseLD: LiveData<WeatherListModel> = MutableLiveData()
    private val _weatherListDetailsLD = MutableLiveData<WeatherListModel>()
    val weatherListDetailsLD: LiveData<WeatherListModel> get() = _weatherListDetailsLD

    private val _segmentedWeatherListDetailsLD = MutableLiveData<List<ForeCastModel>>()
    val segmentedWeatherListDetailsLD: LiveData<List<ForeCastModel>> get() = _segmentedWeatherListDetailsLD

    private var currentWeatherResponseLD: LiveData<CurrentWeatherModel> = MutableLiveData()
    private val _currentWeatherDetailsLD = MutableLiveData<CurrentWeatherModel>()
    val currentWeatherDetailsLD: LiveData<CurrentWeatherModel> get() = _currentWeatherDetailsLD
    private val _todayDate: LiveData<Date> = MutableLiveData(Date())
    val todayDate: LiveData<String> = Transformations.map(_todayDate) {
        it.dateInStringFormat()
    }

    private val _error = MutableLiveData<ErrorModel>()
    val error: LiveData<ErrorModel> get() = _error

    fun requestForCastedWeather(lat: String, lon: String) {
        weatherListResponseLD =
            useCase.getForeCastWeather(
                latitude = lat,
                longitude = lon,
                tempratureUnits = TEMPRATURE_UNITS
            )
        weatherListResponseLD.observeForever(weatherListResponseObserver)
    }

    private val weatherListResponseObserver = Observer<WeatherListModel> {
        if (it.isErrorModel()) {
            _error.value = it.errorModel as ErrorModel
        } else {
            _weatherListDetailsLD.value = it
            //For today forecast list
            setSegmentedForCastList(TODAY_FORECAST)
        }
    }


    fun requestCurrentWeather(lat: String, lon: String) {
        currentWeatherResponseLD =
            useCase.getCurrentWeatherData(
                latitude = lat,
                longitude = lon,
                tempratureUnits = TEMPRATURE_UNITS
            )
        currentWeatherResponseLD.observeForever(currentWeatherResponseObserver)
    }

    private val currentWeatherResponseObserver = Observer<CurrentWeatherModel> {
        if (it.isErrorModel()) {
            _error.value = it.errorModel as ErrorModel
        } else {
            _currentWeatherDetailsLD.value = it
        }
    }

    fun setSegmentedForCastList(code: Int) {
        when (code) {
            //Take First 8 item
            TODAY_FORECAST -> {
                _segmentedWeatherListDetailsLD.value =
                    weatherListDetailsLD.value?.forecastModel?.slice(0..7)
            }
            //Take next 8 item
            TOMORROW_FORECAST -> {
                _segmentedWeatherListDetailsLD.value =
                    weatherListDetailsLD.value?.forecastModel?.slice(8..15)
            }
        }
    }
}