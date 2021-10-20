package com.tobiasstrom.stairs.examplec

import com.tobiasstrom.stairs.examplec.view.CViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cModule = module {
    viewModel<CViewModel>()
}
