package com.tobiasstrom.stairs.examplea.view

import com.tobiasstrom.stairs.common.base.BaseViewModel
import com.tobiasstrom.stairs.common.navigation.ExampleB
import com.tobiasstrom.stairs.common.navigation.NavigationManager

class AViewModel(
    private val _navigationManager: NavigationManager
) : BaseViewModel() {
    fun navigateToB() {
        _navigationManager.navigate(ExampleB)
    }
}
