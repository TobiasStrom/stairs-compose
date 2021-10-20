package com.tobiasstrom.stairs.startup.onboarding

import androidx.annotation.DrawableRes

data class OnboardingPage(
    val title: String,
    val text: String,
    @DrawableRes
    val image: Int,
    val buttonText: String
)
