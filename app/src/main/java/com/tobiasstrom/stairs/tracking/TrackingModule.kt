package com.tobiasstrom.stairs.tracking

import com.tobiasstrom.stairs.tracking.view.TrackingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trackingModule = module {
    viewModel<TrackingViewModel>()
}