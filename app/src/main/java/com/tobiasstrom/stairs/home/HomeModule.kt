package com.tobiasstrom.stairs.home

import com.tobiasstrom.stairs.home.view.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel<HomeViewModel>()
}