package com.mobiquity.test.openweather

import android.app.Application
import com.mobiquity.test.network.basenetwork.AppIdInterceptor
import com.mobiquity.test.network.basenetwork.RequestOfflineInterceptor
import com.mobiquity.test.openweather.dashboard.dependency.DashboardDependencyLoader
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class OpenWeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        loadDependencies()
    }

    // Load all koin modules
    private fun loadDependencies() {
        val commonModule = module {
            single<AppIdInterceptor> {
                AppIdInterceptor()
            }
            single<RequestOfflineInterceptor> {
                RequestOfflineInterceptor(applicationContext)
            }
        }
        startKoin {
            androidLogger()
            androidContext(this@OpenWeatherApp)
            modules(commonModule)
        }
        DashboardDependencyLoader().loadModules()
    }
}