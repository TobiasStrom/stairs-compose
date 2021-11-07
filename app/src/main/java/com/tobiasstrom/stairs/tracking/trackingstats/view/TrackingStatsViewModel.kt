package com.tobiasstrom.stairs.tracking.trackingstats.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.tobiasstrom.stairs.common.base.BaseViewModel
import com.tobiasstrom.stairs.common.data.FirebaseData
import com.tobiasstrom.stairs.tracking.model.TrackedActivity
import kotlinx.coroutines.launch
import timber.log.Timber

class TrackingStatsViewModel(
    private val fireStore: FirebaseData
) : BaseViewModel() {
    var buildingName by mutableStateOf("")
    val updateBuildingName = { _name: String ->
        buildingName = _name
    }

    var startFloor by mutableStateOf("")
    val updateStartFloor = { _startFloor: String ->
        startFloor = _startFloor
    }

    var endFloor by mutableStateOf("")
    val updateEndFloor = { _endFloor: String ->
        endFloor = _endFloor
    }


    fun saveActuvuty() {
        Timber.d(buildingName)
        viewModelScope.launch {
            fireStore.saveActivity(TrackedActivity())
        }
    }
}