package com.tobiasstrom.stairs.common.service

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "prefs")

class DataStorePreferences(
    private val _context: Context,
) : PreferenceService {

    private val _dataStore: DataStore<Preferences> by lazy {
        _context.dataStore
    }


    // Onboarding
    private val _onboardingCompletedKey = booleanPreferencesKey("APP_ONBOARDED")
    override val onboardingCompleted: Flow<Boolean> = _dataStore.data.map { prefs ->
        prefs[_onboardingCompletedKey] ?: false
    }

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        _dataStore.edit { prefs ->
            prefs[_onboardingCompletedKey] = completed
        }
    }

    // Dark mode
    private val _darkModeKey = intPreferencesKey("DARK_THEME_MODE")
    override val darkMode: Flow<DarkModeType> = _dataStore.data.map { prefs ->
        DarkModeType.from(
            prefs[_darkModeKey] ?: DEFAULT_DARK_MODE.flag
        )
    }

    override suspend fun setDarkMode(mode: DarkModeType) {
        _dataStore.edit { prefs ->
            prefs[_darkModeKey] = mode.flag
        }
    }


    // Last building
    private val _lastBuildingKey = stringPreferencesKey("LAST_BUILDING")
    override val lastBuilding: Flow<String?> = _dataStore.data.map { prefs ->
        prefs[_lastBuildingKey]
    }

    override suspend fun setLastBuilding(lastBuilding: String) {
        _dataStore.edit { prefs ->
            prefs[_lastBuildingKey] = lastBuilding
        }
    }

    // Start floor
    private val _startFloorKey = intPreferencesKey("LAST_FLOOR")
    override val startFloor: Flow<Int?> = _dataStore.data.map { prefs ->
        prefs[_startFloorKey]
    }

    override suspend fun setStartFloor(startFloor: Int) {
        _dataStore.edit { prefs ->
            prefs[_startFloorKey] = startFloor
        }
    }

    // End floor
    private val _endFloorKey = intPreferencesKey("LAST_FLOOR")
    override val endFloor: Flow<Int?> = _dataStore.data.map { prefs ->
        prefs[_endFloorKey]
    }

    override suspend fun setEndFloor(endFloor: Int) {
        _dataStore.edit { prefs ->
            prefs[_endFloorKey] = endFloor
        }
    }

    companion object {
        // Change this to set the initial state for Dark Mode
        val DEFAULT_DARK_MODE = DarkModeType.DARK_MODE_OFF
    }

}
