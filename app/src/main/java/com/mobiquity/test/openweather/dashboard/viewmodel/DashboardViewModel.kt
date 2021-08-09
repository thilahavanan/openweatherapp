package com.mobiquity.test.openweather.dashboard.viewmodel

import androidx.lifecycle.*
import com.mobiquity.test.network.basemodels.ErrorModel
import com.mobiquity.test.network.dashboard.models.CityModel
import com.mobiquity.test.network.dashboard.models.CurrentWeatherModel
import com.mobiquity.test.network.dashboard.models.WeatherListModel
import com.mobiquity.test.openweather.dashboard.usecase.DashboardUseCase

const val TEMPRATURE_UNITS = "metric"
class DashboardViewModel(
    private val useCase: DashboardUseCase
) : ViewModel() {
    private var weatherListResponseLD: LiveData<WeatherListModel> = MutableLiveData()
    private val _weatherListDetailsLD = MutableLiveData<WeatherListModel>()
    val weatherListDetailsLD: LiveData<WeatherListModel> get() = _weatherListDetailsLD

    private var currentWeatherResponseLD: LiveData<CurrentWeatherModel> = MutableLiveData()
    private val _currentWeatherDetailsLD = MutableLiveData<CurrentWeatherModel>()
    val currentWeatherDetailsLD: LiveData<CurrentWeatherModel> get() = _currentWeatherDetailsLD

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
    val cityModelLD: LiveData<CityModel> = Transformations.map(_weatherListDetailsLD) {
        it.cityModel
    }
}