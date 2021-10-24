package com.tobiasstrom.stairs.main.view

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tobiasstrom.stairs.R
import com.tobiasstrom.stairs.common.navigation.*
import com.tobiasstrom.stairs.ui.navigation.ShortcutNavHost
import com.tobiasstrom.stairs.ui.theme.ModalScrimColor
import com.tobiasstrom.stairs.ui.theme.ShortcutTheme
import com.tobiasstrom.stairs.ui.theme.SystemGrey_Dark
import timber.log.Timber

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navController: NavHostController,
    bottomSheetState: ModalBottomSheetState,
    scaffoldState: ScaffoldState
) {
    val bottomNavVisible by viewModel.bottomNavVisible.collectAsState(true)
    val bottomSheetContent by viewModel.bottomSheetContent.collectAsState({})
    val isDarkMode by viewModel.isDarkMode.collectAsState(false)
    val isDarkTheme = isDarkMode ?: isSystemInDarkTheme()

    ShortcutTheme(isDarkTheme) {
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetBackgroundColor = MaterialTheme.colors.surface,
            sheetShape = RoundedCornerShape(
                topStartPercent = 10,
                topEndPercent = 10
            ),
            scrimColor = ModalScrimColor,
            sheetContent = {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxSize(0.75f)
                ) {
                    bottomSheetContent()
                }
            }
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = { ShortcutTopBar(navController, viewModel) },
                bottomBar = {
                    if (bottomNavVisible) {
                        ShortcutBottomNavigationBar(navController) { item ->
                            viewModel.onBottomNavItemClicked(item)
                        }
                    }
                }
            ) { paddingValues ->
                rememberSystemUiController().setNavigationBarColor(
                    color = when {
                        MaterialTheme.colors.isLight -> Color.Black
                        bottomNavVisible -> MaterialTheme.colors.surface
                        else -> MaterialTheme.colors.background
                    },
                    darkIcons = !MaterialTheme.colors.isLight
                )

                ShortcutNavHost(navController, paddingValues)
            }
        }
    }
}

@Composable
fun ShortcutTopBar(navController: NavHostController, viewModel: MainViewModel) {
    val topBarVisible by viewModel.topBarVisible.collectAsState(true)
    val topBarTitle by viewModel.topBarTitle.collectAsState("")
    val backButtonVisible by viewModel.backButtonVisible.collectAsState(false)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBackButton = backButtonVisible && navBackStackEntry?.destination?.route != NavigationAction.getStartupActionRoute() && navController.previousBackStackEntry != null

    val topBarActions = navBackStackEntry?.destination?.route?.let { route ->
        NavigationAction.getActionForRoute(route)?.topBarActions
    }

    Timber.i("BackStack destination: ${navBackStackEntry?.destination?.route ?: "null"} - PrevBackStack destination: ${navController.previousBackStackEntry?.destination?.route ?: "null"}")

    rememberSystemUiController().setStatusBarColor(
        color = when {
            topBarVisible -> MaterialTheme.colors.primaryVariant
            else -> MaterialTheme.colors.background
        },
        darkIcons = !MaterialTheme.colors.isLight
    )

    if (topBarVisible) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
            elevation = dimensionResource(R.dimen.dimen_unit),
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = topBarTitle
                )
            },
            navigationIcon = when {
                showBackButton -> {
                    {
                        val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
                        IconButton(
                            onClick = { onBackPressedDispatcher?.onBackPressed() }
                        ) {
                            Icon(
                                Icons.Rounded.ArrowBack,
                                contentDescription = "",
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                }
                else -> null
            },
            actions = topBarActions ?: {}
        )
    }
}

@Composable
fun ShortcutBottomNavigationBar(navController: NavController, onItemClicked: (BottomNavItemHolder) -> Unit) {
    val items = listOf(
        BottomNavItemHolder(
            HomeNav,
            Icons.Rounded.Home,
            stringResource(R.string.home_title)
        ),
        BottomNavItemHolder(
            StatsNav,
            Icons.Outlined.ArrowForward,
            stringResource(R.string.stats_title)
        )
    )

    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = dimensionResource(R.dimen.dimen_unit)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.navAction.route,
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = ""
                    )
                },
                label = { Text(item.label) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = SystemGrey_Dark,
                onClick = {
                    onItemClicked(item)
                }
            )
        }
    }
}