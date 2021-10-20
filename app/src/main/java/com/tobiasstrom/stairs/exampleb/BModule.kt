package com.tobiasstrom.stairs.exampleb

import com.tobiasstrom.stairs.exampleb.view.BViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bModule = module {
    viewModel<BViewModel>()
}
