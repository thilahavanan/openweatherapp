package com.mobiquity.test.network.dashboard.repository

import org.koin.core.KoinComponent

class DashboardRepository(
    val delegate: DashboardDelegate
) : KoinComponent, DashboardDelegate by delegate {
}