package com.tobiasstrom.stairs.tracking.track.view

import androidx.lifecycle.viewModelScope
import com.tobiasstrom.stairs.common.base.BaseViewModel
import com.tobiasstrom.stairs.common.data.FirebaseDateImp
import com.tobiasstrom.stairs.common.navigation.NavigationManager
import com.tobiasstrom.stairs.common.navigation.TrackingStatsNav
import com.tobiasstrom.stairs.common.service.AnalyticsEvent
import com.tobiasstrom.stairs.common.service.AnalyticsService
import com.tobiasstrom.stairs.common.service.RemoteConfigService
import com.tobiasstrom.stairs.tracking.model.Time
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime

class TrackingViewModel(
    private val _remoteConfig: RemoteConfigService,
    private val _analytics: AnalyticsService,
    private val _navigationManager: NavigationManager,
) : BaseViewModel() {

    var isTracking = false
    val timer: MutableList<LocalDateTime> = arrayListOf()
    val laps: MutableList<Time> = arrayListOf()
    var time: Time = Time("00", "00", "00")

    private val _viewState = MutableStateFlow<TrackingState>(TrackingState.Default)
    val viewState: StateFlow<TrackingState> get() = _viewState

    fun startTracking() {
        _analytics.logEvent(AnalyticsEvent.StartedTracking)
        timer.clear()
        laps.clear()
        time = Time("00", "00", "00")
        timer.add(LocalDateTime.now())
        isTracking = true
        viewModelScope.launch {
            do {
                delay(83)
                time = timeFromStart()
                _viewState.value = TrackingState.Tracking()
            }
            while (isTracking)
        }
    }

    fun stopTracking() {
        viewModelScope.launch {
            timer.add(LocalDateTime.now())
            isTracking = false
            delay(83)
            _viewState.value = TrackingState.Result(howLongTime())

        }
    }

    fun newFlor() {
        viewModelScope.launch {
            timer.add(LocalDateTime.now())
            laps.add(timeFromLast())
        }
    }

    fun timeFromLast(): Time{
        val duration = Duration.between(timer[timer.size-2], timer[timer.size-1])
        val milli = duration.toMillis() % 100
        val milliS = if(milli<10) "0$milli" else milli.toString()
        val sec = duration.seconds % 60
        val secS = if(sec<10) "0$sec" else sec.toString()
        val min = duration.toMinutes()
        val minS = if(min.toInt() == 0) "00" else if (min < 10) "0$min" else min.toString()
        return Time(minS, secS, milliS)
    }

    fun timeFromStart(): Time{
        val duration = Duration.between(timer[0], LocalDateTime.now())
        val milli = duration.toMillis() % 100
        val milliS = if(milli<10) "0$milli" else milli.toString()
        val sec = duration.seconds % 60
        val secS = if(sec<10) "0$sec" else sec.toString()
        val min = duration.toMinutes()
        val minS = if(min.toInt() == 0) "00" else if (min < 10) "0$min" else min.toString()
        return Time(minS, secS, milliS)
    }

    fun howLongTime(): Time{
        val duration = Duration.between(timer[0], timer.last())
        val milli = duration.toMillis() % 100
        val sec = duration.seconds % 60
        val min = duration.toMinutes()
        return Time(min.toString(), sec.toString(), milli.toString())
    }
    fun navigateToTrackingStats(){
        _navigationManager.navigate(TrackingStatsNav)
    }


}

sealed class TrackingState {
    data class Result(val timer: Time) : TrackingState()
    class Tracking() : TrackingState()
    object Lap : TrackingState()
    object Stop : TrackingState()
    object Default : TrackingState()
}

