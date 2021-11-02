package com.tobiasstrom.stairs.common.service

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.tobiasstrom.stairs.BuildConfig
import com.tobiasstrom.stairs.R
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseRemoteConfigService : RemoteConfigService {
    private val _remoteConfig by lazy { FirebaseRemoteConfig.getInstance() }

    override suspend fun init(): RemoteConfigService =
        suspendCoroutine { c ->
            _remoteConfig.setConfigSettingsAsync(
                FirebaseRemoteConfigSettings.Builder().apply {
                    minimumFetchIntervalInSeconds = 3600
                }.build()
            )
            _remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
            _remoteConfig.fetchAndActivate()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Timber.i("Firebase Remote Config params updated.")
                    } else {
                        Timber.e(task.exception, "Failed to update Firebase Remote Config params.")
                    }
                    c.resume(this)
                }
        }

    override fun isVersionLocked(): Boolean =
        try {
            _remoteConfig.getString("require_update_android").split(Regex(","))
                .contains(BuildConfig.VERSION_CODE.toString())
        } catch (e: Exception) {
            false
        }

    override fun isChristmas(): Boolean =
        try {
            _remoteConfig.getBoolean("is_christmas")
        }catch (e: Exception){
            false
        }
}
