package com.tobiasstrom.stairs.main

import com.tobiasstrom.stairs.main.view.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel<MainViewModel>()
}
