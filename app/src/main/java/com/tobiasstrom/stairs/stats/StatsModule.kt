package com.tobiasstrom.stairs.stats

import com.tobiasstrom.stairs.stats.view.StatsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val statsModule = module {
    viewModel<StatsViewModel>()
}