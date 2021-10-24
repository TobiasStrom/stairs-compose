package com.tobiasstrom.stairs.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.tobiasstrom.stairs.R
import com.tobiasstrom.stairs.common.composable.ShortcutButton
import com.tobiasstrom.stairs.common.navigation.NavigationManager
import org.koin.androidx.compose.get

@Composable
fun Home(
    viewModel: HomeViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShortcutButton(onClick = { viewModel.navigateToTracking() }) {
            Text(stringResource(R.string.home_navigate))
        }
    }
}