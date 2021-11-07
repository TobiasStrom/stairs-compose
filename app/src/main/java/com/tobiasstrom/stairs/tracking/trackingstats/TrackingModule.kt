package com.tobiasstrom.stairs.tracking.trackingstats

import com.tobiasstrom.stairs.tracking.trackingstats.view.TrackingStatsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trackingStatsModule = module {
    viewModel{
        TrackingStatsViewModel(
            fireStore = get()
        )
    }
}