package com.tobiasstrom.stairs.common.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BadgeBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NotificationAdd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptions
import com.tobiasstrom.stairs.R
import org.koin.androidx.compose.get

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

object Splash : NavigationAction() {
    override val route = "splash"
    override val navOptions = NavOptions.Builder().apply {
        setPopUpTo(getStartupActionRoute(), true, false)
        setLaunchSingleTop(true)
        setRestoreState(false)
    }.build()
}

object VersionLock : NavigationAction() {
    override val route = "versionlock"
    override val navOptions = NavOptions.Builder().apply {
        setPopUpTo(getStartupActionRoute(), true, false)
        setLaunchSingleTop(true)
        setRestoreState(false)
    }.build()
}

object Onboarding : NavigationAction() {
    override val route: String = "onboarding"
    override val navOptions = NavOptions.Builder().apply {
        setPopUpTo(getStartupActionRoute(), true, false)
        setLaunchSingleTop(true)
        setRestoreState(false)
    }.build()
}

object ExampleA : NavigationAction() {
    override val route = "example_a"
    override val navOptions = NavOptions.Builder().apply {
        setPopUpTo(getStartupActionRoute(), true, true)
        setLaunchSingleTop(true)
        setRestoreState(true)
    }.build()
}

object ExampleB : NavigationAction() {
    override val route = "example_b"
    override val topBarActions: @Composable (RowScope.() -> Unit) = {
        get<NavigationManager>().let { navManager ->
            BadgeBox(
                modifier = Modifier
                    .padding(end = dimensionResource(R.dimen.dimen_unit))
                    .clickable {
                        navManager.showSnackbarMessage("Example B (NavigationAction) has top bar actions specified!") {
                            navManager.showSnackbarMessage("You pressed \"OK\" :)")
                        }
                    },
                badgeContent = { Text("!") }
            ) {
                Icon(
                    Icons.Rounded.NotificationAdd,
                    contentDescription = ""
                )
            }
        }
    }
}

object ExampleC : NavigationAction() {
    override val route = "example_c"
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
