package com.tobiasstrom.stairs.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tobiasstrom.stairs.common.navigation.*
import com.tobiasstrom.stairs.home.view.Home
import com.tobiasstrom.stairs.startup.onboarding.Onboarding
import com.tobiasstrom.stairs.startup.splash.Splash
import com.tobiasstrom.stairs.startup.versionlock.VersionLock
import com.tobiasstrom.stairs.stats.view.Stats
import com.tobiasstrom.stairs.tracking.track.view.Tracking
import com.tobiasstrom.stairs.tracking.trackingstats.view.TrackingStats
import org.koin.androidx.compose.getViewModel

@Composable
fun ShortcutNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = NavigationAction.getStartupActionRoute()
    ) {
        navigation(SplashNav.route, NavigationAction.getStartupActionRoute()) {
            composable(SplashNav.route) {
                Splash(
                    getViewModel()
                )
            }
            composable(VersionLockNav.route) {
                VersionLock()
            }
            composable(OnboardingNav.route) {
                Onboarding(
                    getViewModel()
                )
            }
        }
        navigation(HomeNav.route, "main") {
            composable(HomeNav.route) {
                Home(
                    getViewModel()
                )
            }
            composable(TrackingNav.route) {
                Tracking(
                    getViewModel()
                )
            }
            composable(StatsNav.route) {
                Stats(
                    getViewModel()
                )
            }

        }

        navigation(TrackingNav.route, "test"){
            composable(TrackingNav.route){
                Tracking(viewModel = getViewModel())
            }
            composable(TrackingStatsNav.route){
                TrackingStats(viewModel = getViewModel())
            }
        }

        // Example of view with with content based on some ID
//        composable(Onboarding.route) { bsEntry ->
//            Onboarding(
//                getViewModel(),
//                bsEntry.arguments?.getString(ViewWithArguments.KEY_ID) ?: ""
//            )
//        }
    }
}