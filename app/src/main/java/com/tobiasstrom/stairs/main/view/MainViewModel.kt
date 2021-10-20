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
            Splash,
            VersionLock,
            Onboarding
        ).contains(action).not()
    }

    val topBarTitle = _navigationManager.navAction.map { action ->
        when (action) {
            is ExampleA -> _context.getString(R.string.a_title)
            is ExampleB -> _context.getString(R.string.b_title)
            is ExampleC -> _context.getString(R.string.c_title)
            else -> ""
        }
    }

    val backButtonVisible = _navigationManager.navAction.map { action ->
        listOf(
            ExampleA,
            ExampleC
        ).contains(action).not()
    }

    val bottomNavVisible = _navigationManager.navAction.map { action ->
        listOf(
            ExampleA,
            ExampleC
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
