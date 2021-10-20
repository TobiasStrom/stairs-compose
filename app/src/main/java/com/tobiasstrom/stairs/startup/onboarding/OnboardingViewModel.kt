package com.tobiasstrom.stairs.startup.onboarding

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.tobiasstrom.stairs.R
import com.tobiasstrom.stairs.common.navigation.ExampleA
import com.tobiasstrom.stairs.common.navigation.NavigationManager
import com.tobiasstrom.stairs.common.service.AnalyticsEvent
import com.tobiasstrom.stairs.common.service.AnalyticsService
import com.tobiasstrom.stairs.common.service.PreferenceService

class OnboardingViewModel(
    context: Context,
    private val _navigationManager: NavigationManager,
    private val _analytics: AnalyticsService,
    private val _preferences: PreferenceService
) : ViewModel() {
    val pages = listOf(
        OnboardingPage(
            context.getString(R.string.onboarding_1_title),
            context.getString(R.string.onboarding_1_text),
            R.drawable.ic_launcher_background,
            context.getString(R.string.onboarding_button)
        ),
        OnboardingPage(
            context.getString(R.string.onboarding_2_title),
            context.getString(R.string.onboarding_2_text),
            R.drawable.ic_launcher_background,
            context.getString(R.string.onboarding_button)
        ),
        OnboardingPage(
            context.getString(R.string.onboarding_3_title),
            context.getString(R.string.onboarding_3_text),
            R.drawable.ic_launcher_background,
            context.getString(R.string.onboarding_button_last)
        )
    )

    fun finish() {
        viewModelScope.launch(Dispatchers.IO) {
            _preferences.setOnboardingCompleted(true)
            _analytics.logEvent(AnalyticsEvent.OnboardingComplete)
            _navigationManager.navigate(ExampleA)
        }
    }
}
