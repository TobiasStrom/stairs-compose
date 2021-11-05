package com.tobiasstrom.stairs.tracking.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tobiasstrom.stairs.R
import com.tobiasstrom.stairs.common.composable.ShortcutButton
import kotlinx.coroutines.flow.first

@Composable
fun Tracking(
    @Suppress("UNUSED_PARAMETER")
    viewModel: TrackingViewModel
) {
    //val state = viewModel.viewState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = dimensionResource(id = R.dimen.dimen_2x)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(dimensionResource(R.dimen.dimen_2x)),
            text = stringResource(R.string.b_text),
            textAlign = TextAlign.Center
        )
        TrackingButton(viewModel = viewModel)
    }

}

@Composable
fun TrackingButton(viewModel: TrackingViewModel) {
    val state = viewModel.viewState.collectAsState().value

    when (state) {
        TrackingState.Tracking, TrackingState.Lap-> {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                ShortcutButton(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_unit)),
                    onClick = { viewModel.newFlor() }) {
                    Text(text = "New Flor")
                }
                ShortcutButton(onClick = { viewModel.stopTracking() }) {
                    Text(text = "Stop Tracking")
                }
            }

        }
        else -> {
            ShortcutButton(onClick = { viewModel.startTracking() }) {
                Text(text = "Start Tracking")
            }
        }
    }
}
