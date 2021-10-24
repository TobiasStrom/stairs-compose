package com.tobiasstrom.stairs.home.view

import com.tobiasstrom.stairs.common.base.BaseViewModel
import com.tobiasstrom.stairs.common.navigation.NavigationManager
import com.tobiasstrom.stairs.common.navigation.TrackingNav

class HomeViewModel(
    private val _navigationManager: NavigationManager
) : BaseViewModel() {
    fun navigateToTracking() {
        _navigationManager.navigate(TrackingNav)
    }
}