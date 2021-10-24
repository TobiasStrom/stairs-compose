package com.tobiasstrom.stairs.tracking.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.tobiasstrom.stairs.R

@Composable
fun Tracking(
    @Suppress("UNUSED_PARAMETER")
    viewModel: TrackingViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(dimensionResource(R.dimen.dimen_2x)),
            text = stringResource(R.string.b_text),
            textAlign = TextAlign.Center
        )
    }
}