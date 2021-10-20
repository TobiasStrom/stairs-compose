package com.tobiasstrom.stairs.main.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import com.tobiasstrom.stairs.common.navigation.NavigationAction
import com.tobiasstrom.stairs.common.navigation.NavigationManager
import com.tobiasstrom.stairs.common.service.DarkModeService
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber
import kotlin.NullPointerException

class MainActivity : AppCompatActivity() {
    private val _navigationManager: NavigationManager by inject()
    private val _darkModeService: DarkModeService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.let { bundle ->
            bundle.getString(KEY_CURRENT_NAV_ROUTE)?.let { route ->
                NavigationAction.getActionForRoute(route)?.let { action ->
                    Timber.i("NavAction restored: '$action'")
                    _navigationManager.navigate(action)
                }
            }
        }

        _darkModeService.initializeDarkMode()

        setContent {
            val navController = rememberNavController()

            val bottomSheetState = rememberModalBottomSheetState(
                ModalBottomSheetValue.Hidden,
                confirmStateChange = {
                    // Disallow 'HalfExpanded' state
                    it == ModalBottomSheetValue.Expanded || it == ModalBottomSheetValue.Hidden
                }
            )
            val bottomSheetScope = rememberCoroutineScope()
            val scaffoldState = rememberScaffoldState()
            val snackbarScope = rememberCoroutineScope()

            val currentNavAction = _navigationManager.navAction.collectAsState(null).value.also { action ->
                try {
                    if (action == null) return@also
                    Timber.i("NavAction changed: '$action'")
                    if (action.route.isNotEmpty()) {
                        navController.currentDestination?.route?.let { route ->
                            if (route == action.route) return@also
                        }

                        Timber.i("Navigating to '${action.route}'")
                        navController.navigate(action.route, action.navOptions)
                    }
                } catch (e: Exception) {
                    when (e) {
                        is NullPointerException ->
                            Timber.e(e, "Exception thrown while trying to navigate to current action ('$action'). Most likely NavController.graph is 'null' at this point.")
                        else ->
                            Timber.e(e, "Exception thrown while trying to navigate to current action ('$action')")
                    }
                }
            }

            // LaunchedEffect is run during first composition and never again. Use to initialize certain properties.
            LaunchedEffect(true) {
                _navigationManager.initializeBottomSheetState(bottomSheetState, bottomSheetScope)
                _navigationManager.initializeScaffold(scaffoldState, snackbarScope)

                navController.addOnDestinationChangedListener { _, destination, _ ->
                    if (currentNavAction?.route != destination.route) {
                        NavigationAction.getActionForRoute(destination.route ?: "")?.let { action ->
                            _navigationManager.navigate(action)
                        }
                    }
                }
            }

            MainScreen(getViewModel(), navController, bottomSheetState, scaffoldState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        lifecycleScope.launch(Dispatchers.IO) {
            _navigationManager.navAction.first().let { action ->
                outState.putString(KEY_CURRENT_NAV_ROUTE, action.route)
                Timber.i("NavAction saved: '$action'")
            }
        }
    }

    companion object {
        private const val KEY_CURRENT_NAV_ROUTE = "current_nav_route"
    }
}
