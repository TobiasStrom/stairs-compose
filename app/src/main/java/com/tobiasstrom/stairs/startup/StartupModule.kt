package com.tobiasstrom.stairs.startup

import com.tobiasstrom.stairs.startup.onboarding.OnboardingViewModel
import com.tobiasstrom.stairs.startup.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val startupModule = module {
    viewModel<SplashViewModel>()
    viewModel<OnboardingViewModel>()
}
