package com.tobiasstrom.stairs.common.service

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

@SuppressLint("MissingPermission")
class FirebaseAnalyticsService(context: Context) : AnalyticsService {
    private val _analytics by lazy { FirebaseAnalytics.getInstance(context) }

    override fun logEvent(event: AnalyticsEvent) {
        _analytics.logEvent(event.key, Bundle())
    }
}
