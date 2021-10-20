package com.tobiasstrom.stairs.common.navigation

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import com.tobiasstrom.stairs.R
import com.tobiasstrom.stairs.common.utils.CommonUtils.createSnackbarData
import com.tobiasstrom.stairs.common.utils.CommonUtils.isEqual

class NavigationManager(
    private val _context: Context
) {
    private val _navAction = MutableStateFlow<NavigationAction>(AppStartup)
    val navAction = _navAction.map { it }

    private val _topBarActions = MutableStateFlow<@Composable (RowScope.() -> Unit)> {}
    val topBarActions = _topBarActions.map { it }

    private lateinit var _bottomSheetState: MutableStateFlow<ModalBottomSheetState>
    private lateinit var _bottomSheetScope: CoroutineScope

    private val _bottomSheetContent = MutableStateFlow<@Composable (() -> Unit)> {}
    val bottomSheetContent = _bottomSheetContent.map { it }

    private lateinit var _scaffoldState: MutableStateFlow<ScaffoldState>
    private lateinit var _snackbarScope: CoroutineScope

    fun initializeBottomSheetState(state: ModalBottomSheetState, scope: CoroutineScope) {
        _bottomSheetState = MutableStateFlow(state)
        _bottomSheetScope = scope
    }

    fun initializeScaffold(state: ScaffoldState, snackbarScope: CoroutineScope) {
        _scaffoldState = MutableStateFlow(state)
        _snackbarScope = snackbarScope
    }

    fun navigate(navAction: NavigationAction) {
        _navAction.tryEmit(navAction)
        dismissSnackbar()
    }

    fun showBottomSheet(content: @Composable (() -> Unit)) {
        _bottomSheetState.value.let { state ->
            if (state.isAnimationRunning) return
            _bottomSheetContent.value = {
                BackHandler(state.isVisible) {
                    hideBottomSheet()
                }
                content()
            }
            _bottomSheetScope.launch(Dispatchers.IO) {
                if (state.isVisible) {
                    state.hide()
                }
                state.animateTo(ModalBottomSheetValue.Expanded)
            }
        }
    }

    fun hideBottomSheet() {
        _bottomSheetState.value.let { state ->
            if (state.isAnimationRunning || !state.isVisible) return
            _bottomSheetScope.launch(Dispatchers.IO) {
                state.hide()
            }
        }
    }

    fun showSnackbarMessage(message: String, duration: SnackbarDuration? = null, actionText: String? = null, actionCallback: (() -> Unit)? = null) {
        _scaffoldState.value.let { state ->
            // Ensure no duplicates
            val dataIsEqualToCurrent = state.snackbarHostState.currentSnackbarData?.let { currentData ->
                val newData = createSnackbarData(
                    message,
                    duration ?: actionCallback?.let { SnackbarDuration.Indefinite } ?: SnackbarDuration.Short,
                    actionText ?: _context.getString(R.string.general_ok)
                )
                currentData.isEqual(newData)
            }
            if (dataIsEqualToCurrent == true) return@let

            _snackbarScope.launch {
                val snackbarResult = state.snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionText ?: actionCallback?.let { _context.getString(R.string.general_ok) },
                    duration = duration ?: actionCallback?.let { SnackbarDuration.Indefinite } ?: SnackbarDuration.Short
                )
                when (snackbarResult) {
                    SnackbarResult.ActionPerformed -> actionCallback?.let { it() }
                    else -> {}
                }
            }
        }
    }

    fun dismissSnackbar() {
        _scaffoldState.value.let { state ->
            // Dismiss any active snackbar on destination changed
            state.snackbarHostState.currentSnackbarData?.dismiss()
        }
    }
}
