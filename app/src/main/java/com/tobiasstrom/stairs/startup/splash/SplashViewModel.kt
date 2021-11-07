package com.tobiasstrom.stairs.startup.splash

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import com.tobiasstrom.stairs.common.base.BaseViewModel
import com.tobiasstrom.stairs.common.navigation.HomeNav
import com.tobiasstrom.stairs.common.navigation.NavigationManager
import com.tobiasstrom.stairs.common.navigation.OnboardingNav
import com.tobiasstrom.stairs.common.navigation.VersionLockNav
import com.tobiasstrom.stairs.common.service.PreferenceService
import com.tobiasstrom.stairs.common.service.RemoteConfigService

class SplashViewModel(
    remoteConfig: RemoteConfigService,
    preferences: PreferenceService,
    navigationManager: NavigationManager
) : BaseViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            remoteConfig.init()
            when {
                remoteConfig.isVersionLocked() ->
                    navigationManager.navigate(VersionLockNav)
                !preferences.onboardingCompleted.first() ->
                    navigationManager.navigate(OnboardingNav)
                else ->
                    navigationManager.navigate(HomeNav)
            }
        }
    }
}