package com.tobiasstrom.stairs.tracking.view

import androidx.compose.runtime.remember
import androidx.lifecycle.viewModelScope
import com.tobiasstrom.stairs.common.base.BaseViewModel
import com.tobiasstrom.stairs.common.service.AnalyticsEvent
import com.tobiasstrom.stairs.common.service.AnalyticsService
import com.tobiasstrom.stairs.common.service.FirebaseAnalyticsService
import com.tobiasstrom.stairs.common.service.RemoteConfigService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TrackingViewModel(
    private val _remoteConfig: RemoteConfigService,
    private val _analytics: AnalyticsService,
) : BaseViewModel() {

    var tracking = false

    private val _viewState = MutableStateFlow(TrackingState.Default)
    val viewState: StateFlow<TrackingState> get() = _viewState

    fun startTracking() {
        _analytics.logEvent(AnalyticsEvent.StartedTracking)
        viewModelScope.launch {
            _viewState.value = TrackingState.Tracking
        }
        tracking = true
    }
    fun stopTracking(){
        viewModelScope.launch {
            _viewState.value =TrackingState.Stop
            tracking = false
        }

    }

    fun newFlor(){
        viewModelScope.launch {
            _viewState.value = TrackingState.Lap
        }
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

enum class TrackingState{
    Loading,
    Tracking,
    Lap,
    Stop,
    Default
}