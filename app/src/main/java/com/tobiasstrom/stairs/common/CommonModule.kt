package com.tobiasstrom.stairs.common

import com.tobiasstrom.stairs.common.data.FirebaseData
import com.tobiasstrom.stairs.common.data.FirebaseDateImp
import com.tobiasstrom.stairs.common.navigation.NavigationManager
import com.tobiasstrom.stairs.common.service.*
import org.koin.dsl.module

val commonModule = module {
    single<RemoteConfigService> { FirebaseRemoteConfigService() }
    single<AnalyticsService> { FirebaseAnalyticsService(get()) }
    single<PreferenceService> { DataStorePreferences(get()) }
    single<ReviewService> { GoogleReviewService(get()) }
    single { DarkModeService(get(), get()) }
    single { NavigationManager(get()) }
    single<FirebaseData> { FirebaseDateImp()}
}
