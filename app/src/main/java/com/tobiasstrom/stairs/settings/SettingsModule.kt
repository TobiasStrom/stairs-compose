package com.tobiasstrom.stairs.settings

import com.tobiasstrom.stairs.settings.view.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    viewModel<SettingsViewModel>()
}