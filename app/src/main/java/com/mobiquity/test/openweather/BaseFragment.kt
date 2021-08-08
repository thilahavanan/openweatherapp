package com.mobiquity.test.openweather

import androidx.fragment.app.Fragment
import androidx.navigation.NavController

open class BaseFragment : Fragment() {

    protected fun navController(): NavController? {
        return (requireActivity() as MainActivity).navController
    }

}