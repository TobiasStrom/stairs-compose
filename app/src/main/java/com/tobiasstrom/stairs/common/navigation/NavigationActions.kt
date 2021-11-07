package com.tobiasstrom.stairs.common.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptions

sealed class NavigationAction {
    open val route: String = ""
    open val arguments: List<NamedNavArgument> = emptyList()
    open val navOptions: NavOptions? = null
    open val topBarActions: @Composable (RowScope.() -> Unit) = {}

    companion object {
        fun getStartupAction() = AppStartup
        fun getStartupActionRoute() = getStartupAction().route
        fun getActionForRoute(route: String) = NavActions.firstOrNull { it.route == route }
    }
}

val NavActions by lazy {
    NavigationAction::class.sealedSubclasses.mapNotNull { it.objectInstance }.toSet()
}

object AppStartup : NavigationAction() {
    override val route = "startup"
}

object SplashNav : NavigationAction() {
    override val route = "splash"
    override val navOptions = NavOptions.Builder().apply {
        setPopUpTo(getStartupActionRoute(), true, false)
        setLaunchSingleTop(true)
        setRestoreState(false)
    }.build()
}

object VersionLockNav : NavigationAction() {
    override val route = "versionlock"
    override val navOptions = NavOptions.Builder().apply {
        setPopUpTo(getStartupActionRoute(), true, false)
        setLaunchSingleTop(true)
        setRestoreState(false)
    }.build()
}

object OnboardingNav : NavigationAction() {
    override val route: String = "onboarding"
    override val navOptions = NavOptions.Builder().apply {
        setPopUpTo(getStartupActionRoute(), true, false)
        setLaunchSingleTop(true)
        setRestoreState(false)
    }.build()
}

object HomeNav : NavigationAction() {
    override val route = "home"
    override val navOptions = NavOptions.Builder().apply {
        setPopUpTo(getStartupActionRoute(), true, true)
        setLaunchSingleTop(true)
        setRestoreState(true)
    }.build()
}

object TrackingNav : NavigationAction() {
    override val route = "tracking"
    override val navOptions = NavOptions.Builder().apply {
        setPopUpTo(getStartupActionRoute(), true, true)
        setLaunchSingleTop(true)
        setRestoreState(true)
    }.build()
}

object TrackingStatsNav : NavigationAction() {
    override val route = "trackingstats"
    override val navOptions = NavOptions.Builder().apply {
        setPopUpTo(getStartupActionRoute(), true, true)
        setLaunchSingleTop(true)
        setRestoreState(true)
    }.build()

}

object StatsNav : NavigationAction() {
    override val route = "stats"
    override val navOptions = NavOptions.Builder().apply {
        setPopUpTo(getStartupActionRoute(), true, true)
        setLaunchSingleTop(true)
        setRestoreState(true)
    }.build()
}

// Example of view with with content based on some ID
//    object ViewWithArguments : NavigationAction() {
//        const val KEY_ROUTE = "someview"
//        const val KEY_ID = "someview_id"
//
//        override val route: String = "$KEY_ROUTE/{$KEY_ID}"
//        override val arguments: List<NamedNavArgument> = listOf(
//            navArgument(KEY_ID) { type = NavType.StringType }
//        )
//
//        fun withId(id: String) = object : NavigationAction {
//            override val route: String = "$KEY_ROUTE/id"
//            override val arguments: List<NamedNavArgument> = this@ViewWithArguments.arguments
//            override val navOptions: NavOptions = this@ViewWithArguments.navOptions
//        }
//    }