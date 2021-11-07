package com.tobiasstrom.stairs.settings.view

import androidx.lifecycle.viewModelScope
import com.tobiasstrom.stairs.common.base.BaseViewModel
import com.tobiasstrom.stairs.common.data.FirebaseData
import com.tobiasstrom.stairs.common.service.DarkModeService
import com.tobiasstrom.stairs.common.service.DarkModeType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val _darkModeService: DarkModeService,
) : BaseViewModel() {

    val isCurrentlyInDarkMode =
        _darkModeService.isCurrentlyInDarkMode.asState(false)

    val isCurrentlyDarkModeFollowSystem = _darkModeService.darkModeSetting.map { mode ->
        mode == DarkModeType.DARK_MODE_FOLLOW_SYSTEM
    }.asState(false)

    fun toggleDarkMode() {
        _darkModeService.toggleDarkMode()
    }

    fun toggleDarkModeFollowSystem() {
        viewModelScope.launch(Dispatchers.IO) {
            _darkModeService.setDarkMode(
                when (isCurrentlyDarkModeFollowSystem.value) {
                    true -> when (isCurrentlyInDarkMode.value) {
                        true -> DarkModeType.DARK_MODE_ON
                        else -> DarkModeType.DARK_MODE_OFF
                    }
                    else -> DarkModeType.DARK_MODE_FOLLOW_SYSTEM
                }
            )
        }
    }
}