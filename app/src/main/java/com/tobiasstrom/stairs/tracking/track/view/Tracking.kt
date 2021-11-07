package com.tobiasstrom.stairs.tracking.track.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tobiasstrom.stairs.R
import com.tobiasstrom.stairs.common.composable.ShortcutButton
import com.tobiasstrom.stairs.tracking.components.TimerClock
import com.tobiasstrom.stairs.tracking.components.TimerClockLap

@Composable
fun Tracking(
    @Suppress("UNUSED_PARAMETER")
    viewModel: TrackingViewModel
) {
    val state = viewModel.viewState.collectAsState().value
    val laps = viewModel.laps.reversed()
    val time = viewModel.time

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = dimensionResource(id = R.dimen.dimen_2x)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(dimensionResource(R.dimen.dimen_2x)),
            text = stringResource(R.string.b_text),
            textAlign = TextAlign.Center
        )
        if (state is TrackingState.Tracking || state is TrackingState.Result) {
            TimerClock(time = time, modifier = Modifier.padding(1.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(200.dp)
            ) {
                items(laps) { lap ->
                    TimerClockLap(time = lap, modifier = Modifier.padding(1.dp))
                }
            }
        }
        Spacer(modifier = Modifier.weight(1.0f))

        TrackingButton(viewModel = viewModel)
    }

}

@Composable
fun TrackingButton(viewModel: TrackingViewModel) {
    val state = viewModel.viewState.collectAsState().value

    when (state) {
        is TrackingState.Tracking, TrackingState.Lap -> {
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
        is TrackingState.Result -> {
            ShortcutButton(onClick = { viewModel.navigateToTrackingStats() }) {
                Text(text = "Show Result")
            }
        }
        else -> {
            ShortcutButton(onClick = { viewModel.startTracking() }) {
                Text(text = "Start Tracking")
            }
        }
    }
}





