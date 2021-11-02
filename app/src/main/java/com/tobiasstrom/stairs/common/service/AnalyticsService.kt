package com.tobiasstrom.stairs.common.service

interface AnalyticsService {
    fun logEvent(event: AnalyticsEvent)
}

enum class AnalyticsEvent(val key: String) {
    OnboardingComplete("onboarding_complete"),
    StartedTracking("started_tracking")
}
