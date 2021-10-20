package com.tobiasstrom.stairs.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tobiasstrom.stairs.common.navigation.ExampleA
import com.tobiasstrom.stairs.common.navigation.ExampleB
import com.tobiasstrom.stairs.common.navigation.ExampleC
import com.tobiasstrom.stairs.common.navigation.NavigationAction
import com.tobiasstrom.stairs.common.navigation.Onboarding
import com.tobiasstrom.stairs.common.navigation.Splash
import com.tobiasstrom.stairs.common.navigation.VersionLock
import com.tobiasstrom.stairs.examplea.view.ExampleA
import com.tobiasstrom.stairs.exampleb.view.ExampleB
import com.tobiasstrom.stairs.examplec.view.ExampleC
import com.tobiasstrom.stairs.startup.onboarding.Onboarding
import com.tobiasstrom.stairs.startup.splash.Splash
import com.tobiasstrom.stairs.startup.versionlock.VersionLock
import org.koin.androidx.compose.getViewModel

@Composable
fun ShortcutNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = NavigationAction.getStartupActionRoute()
    ) {
        navigation(Splash.route, NavigationAction.getStartupActionRoute()) {
            composable(Splash.route) {
                Splash(
                    getViewModel()
                )
            }
            composable(VersionLock.route) {
                VersionLock()
            }
            composable(Onboarding.route) {
                Onboarding(
                    getViewModel()
                )
            }
        }
        navigation(ExampleA.route, "main") {
            composable(ExampleA.route) {
                ExampleA(
                    getViewModel()
                )
            }
            composable(ExampleB.route) {
                ExampleB(
                    getViewModel()
                )
            }
            composable(ExampleC.route) {
                ExampleC(
                    getViewModel()
                )
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
