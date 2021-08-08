package com.mobiquity.test.openweather.dashboard.dependency

import com.mobiquity.test.network.dashboard.datasource.DashboardDataSourceNetworkImpl
import com.mobiquity.test.network.dashboard.repository.DashboardDelegate
import com.mobiquity.test.network.dashboard.repository.DashboardDelegateImpl
import com.mobiquity.test.network.dashboard.repository.DashboardRepository
import com.mobiquity.test.openweather.dashboard.usecase.DashboardUseCase
import com.mobiquity.test.openweather.dashboard.viewmodel.DashboardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel(override = true) {
        DashboardViewModel(
            useCase = get()
        )
    }
}

val usecaseModule = module {
    factory {
        DashboardUseCase(dashboardRepository = get())
    }
}

val repositoryModule = module {
    single { DashboardRepository(delegate = get()) }

    single<DashboardDelegate> {
        DashboardDelegateImpl(dataSourceNetwork = get())
    }
}

val networkModule = module {
    single {
        DashboardDataSourceNetworkImpl()
    }
}
