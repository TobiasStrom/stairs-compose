package com.tobiasstrom.stairs.tracking.view

import com.tobiasstrom.stairs.common.base.BaseViewModel
import com.tobiasstrom.stairs.common.service.AnalyticsEvent
import com.tobiasstrom.stairs.common.service.AnalyticsService
import com.tobiasstrom.stairs.common.service.FirebaseAnalyticsService
import com.tobiasstrom.stairs.common.service.RemoteConfigService

class TrackingViewModel(
    private val _remoteConfig: RemoteConfigService,
    private val _analytics: AnalyticsService,
) : BaseViewModel() {
    fun startTracking() {
        _analytics.logEvent(AnalyticsEvent.StartedTracking)
    }

    fun getTextForButton(): String{
        val isChristmas = _remoteConfig.isChristmas()
        return if(isChristmas){
            "Start Christmas tracking"
        }else{
            "Start tracking"
        }
    }

}