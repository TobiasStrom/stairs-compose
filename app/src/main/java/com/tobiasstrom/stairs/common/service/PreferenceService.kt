package com.tobiasstrom.stairs.common.service

import kotlinx.coroutines.flow.Flow

interface PreferenceService {
    val onboardingCompleted: Flow<Boolean>
    suspend fun setOnboardingCompleted(completed: Boolean)

    val darkMode: Flow<DarkModeType>
    suspend fun setDarkMode(mode: DarkModeType)

    val lastBuilding: Flow<String?>
    suspend fun setLastBuilding(lastBuilding: String)

    val startFloor: Flow<Int?>
    suspend fun setStartFloor(startFloor: Int)

    val endFloor: Flow<Int?>
    suspend fun setEndFloor(endFloor: Int)

}
