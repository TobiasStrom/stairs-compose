package com.tobiasstrom.stairs.examplea

import com.tobiasstrom.stairs.examplea.view.AViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val aModule = module {
    viewModel<AViewModel>()
}
