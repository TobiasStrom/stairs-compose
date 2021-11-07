package com.tobiasstrom.stairs.stats.view

import androidx.lifecycle.viewModelScope
import com.tobiasstrom.stairs.common.base.BaseViewModel
import com.tobiasstrom.stairs.common.data.FirebaseData
import com.tobiasstrom.stairs.tracking.model.TrackedActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class StatsViewModel(private val firebase: FirebaseData) : BaseViewModel() {

    private val _viewState = MutableStateFlow<ResultState>(ResultState.Loading)
    val viewState: StateFlow<ResultState> get() = _viewState

    init {
        getData()
    }


    fun getData(){
        Timber.d("getData")
        viewModelScope.launch {
            val data = firebase.getActivity()
            _viewState.value = ResultState.Loaded(data = data)
        }
    }
}

sealed class ResultState{
    data class Loaded(val data: List<TrackedActivity>): ResultState()
    object Loading: ResultState()
}