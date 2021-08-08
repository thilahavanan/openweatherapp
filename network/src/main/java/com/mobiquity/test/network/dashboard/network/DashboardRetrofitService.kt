package com.mobiquity.test.network.dashboard.network

import com.mobiquity.test.network.basenetwork.BaseRetrofitService

class DashboardRetrofitService : BaseRetrofitService<DashboardRetrofitApi>(
    interceptorList = listOf(),
    api = DashboardRetrofitApi::class.java
)
