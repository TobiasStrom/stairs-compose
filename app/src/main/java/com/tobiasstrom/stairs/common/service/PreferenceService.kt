package com.tobiasstrom.stairs.common.service

import kotlinx.coroutines.flow.Flow

interface PreferenceService {
    val onboardingCompleted: Flow<Boolean>
    suspend fun setOnboardingCompleted(completed: Boolean)

    val darkMode: Flow<DarkModeType>
    suspend fun setDarkMode(mode: DarkModeType)
}
