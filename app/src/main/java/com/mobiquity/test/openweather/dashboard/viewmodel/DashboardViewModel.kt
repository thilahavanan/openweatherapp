package com.mobiquity.test.openweather.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.mobiquity.test.network.basemodels.ErrorModel
import com.mobiquity.test.network.dashboard.models.WeatherListModel
import com.mobiquity.test.openweather.dashboard.usecase.DashboardUseCase

class DashboardViewModel(
    private val useCase: DashboardUseCase
) : ViewModel() {
    private var weatherListResponseLD: LiveData<WeatherListModel> = MutableLiveData()
    private val _weatherListDetailsLD = MutableLiveData<WeatherListModel>()
    val weatherListDetailsLD: LiveData<WeatherListModel> get() = _weatherListDetailsLD

    private val _error = MutableLiveData<ErrorModel>()
    val error: LiveData<ErrorModel> get() = _error

    fun requestWeather() {
        weatherListResponseLD =
            useCase.getForeCastWeather(latitude = "10.786999", longitude = "79.13")
        weatherListResponseLD.observeForever(weatherListResponseObserver)
    }

    private val weatherListResponseObserver = Observer<WeatherListModel> {
        if (it.isErrorModel()) {
            _error.value = it.errorModel as ErrorModel
        } else {
            _weatherListDetailsLD.value = it
        }
    }
}