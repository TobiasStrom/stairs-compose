package com.tobiasstrom.stairs.common.service

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
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

    private val _onboardingCompletedKey = booleanPreferencesKey("APP_ONBOARDED")
    override val onboardingCompleted: Flow<Boolean> = _dataStore.data.map { prefs ->
        prefs[_onboardingCompletedKey] ?: false
    }

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        _dataStore.edit { prefs ->
            prefs[_onboardingCompletedKey] = completed
        }
    }

    private val darkModeKey = intPreferencesKey("DARK_THEME_MODE")
    override val darkMode: Flow<DarkModeType> = _dataStore.data.map { prefs ->
        DarkModeType.from(
            prefs[darkModeKey] ?: DEFAULT_DARK_MODE.flag
        )
    }

    override suspend fun setDarkMode(mode: DarkModeType) {
        _dataStore.edit { prefs ->
            prefs[darkModeKey] = mode.flag
        }
    }

    companion object {
        // Change this to set the initial state for Dark Mode
        val DEFAULT_DARK_MODE = DarkModeType.DARK_MODE_OFF
    }
}
