package com.tobiasstrom.stairs.main.view

import android.content.Context
import kotlinx.coroutines.flow.map
import com.tobiasstrom.stairs.R
import com.tobiasstrom.stairs.common.base.BaseViewModel
import com.tobiasstrom.stairs.common.navigation.*
import com.tobiasstrom.stairs.common.service.DarkModeService
import com.tobiasstrom.stairs.common.service.DarkModeType

class MainViewModel(
    private val _context: Context,
    private val _navigationManager: NavigationManager,
    private val _darkModeService: DarkModeService
) : BaseViewModel() {
    val topBarVisible = _navigationManager.navAction.map { action ->
        listOf(
            SplashNav,
            VersionLockNav,
            OnboardingNav
        ).contains(action).not()
    }

    val topBarTitle = _navigationManager.navAction.map { action ->
        when (action) {
            is HomeNav -> _context.getString(R.string.home_title)
            is TrackingNav -> _context.getString(R.string.tracking_title)
            is StatsNav -> _context.getString(R.string.stats_title)
            else -> ""
        }
    }

    val backButtonVisible = _navigationManager.navAction.map { action ->
        listOf(
            HomeNav,
            StatsNav
        ).contains(action).not()
    }

    val bottomNavVisible = _navigationManager.navAction.map { action ->
        listOf(
            HomeNav,
            StatsNav
        ).contains(action)
    }

    val bottomSheetContent = _navigationManager.bottomSheetContent.map { it }

    val isDarkMode = _darkModeService.darkModeSetting.map { mode ->
        when (mode) {
            DarkModeType.DARK_MODE_ON -> true
            DarkModeType.DARK_MODE_OFF -> false
            else -> null
        }
    }

    fun onBottomNavItemClicked(item: BottomNavItemHolder) {
        _navigationManager.navigate(item.navAction)
    }
}