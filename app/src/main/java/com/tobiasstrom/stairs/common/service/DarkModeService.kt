package com.tobiasstrom.stairs.common.service

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DarkModeService(
    private val _context: Context,
    private val _preferences: PreferenceService
) {
    private var _coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    val darkModeSetting = _preferences.darkMode
    val isCurrentlyInDarkMode = darkModeSetting.map { mode ->
        when (mode) {
            DarkModeType.DARK_MODE_ON -> true
            DarkModeType.DARK_MODE_OFF -> false
            DarkModeType.DARK_MODE_FOLLOW_SYSTEM -> isSystemCurrentlyInDarkMode()
        }
    }

    fun initializeDarkMode() {
        _coroutineScope.launch {
            setDarkModeSetting(_preferences.darkMode.first())
        }
    }

    fun setDarkMode(mode: DarkModeType) {
        _coroutineScope.launch {
            setDarkModeSetting(mode)
        }
    }

    fun toggleDarkMode() {
        _coroutineScope.launch {
            setDarkModeSetting(
                when (isSystemCurrentlyInDarkMode()) {
                    true -> DarkModeType.DARK_MODE_OFF
                    false -> DarkModeType.DARK_MODE_ON
                }
            )
        }
    }

    private fun isSystemCurrentlyInDarkMode(): Boolean =
        when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> true
            AppCompatDelegate.MODE_NIGHT_NO -> false
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    _context.resources.configuration.isNightModeActive
                } else {
                    when (_context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                        Configuration.UI_MODE_NIGHT_YES -> true
                        Configuration.UI_MODE_NIGHT_NO -> false
                        else -> false
                    }
                }
            }
        }

    private suspend fun setDarkModeSetting(mode: DarkModeType) {
        _preferences.setDarkMode(mode)
        AppCompatDelegate.setDefaultNightMode(mode.toAppCompatFlag())
    }
}

enum class DarkModeType(val flag: Int) {
    DARK_MODE_FOLLOW_SYSTEM(-1),
    DARK_MODE_OFF(1),
    DARK_MODE_ON(2);

    companion object {
        fun from(intFlag: Int): DarkModeType =
            when (intFlag) {
                -1 -> DARK_MODE_FOLLOW_SYSTEM
                2 -> DARK_MODE_ON
                else -> DARK_MODE_OFF
            }
    }

    fun toAppCompatFlag(): Int =
        when (this) {
            DARK_MODE_ON -> AppCompatDelegate.MODE_NIGHT_YES
            DARK_MODE_OFF -> AppCompatDelegate.MODE_NIGHT_NO
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
}
