package com.tobiasstrom.stairs.tracking.track

import com.tobiasstrom.stairs.tracking.track.view.TrackingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trackModule = module {
    viewModel<TrackingViewModel>()
}