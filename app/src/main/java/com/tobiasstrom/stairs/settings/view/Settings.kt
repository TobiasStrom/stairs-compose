package com.tobiasstrom.stairs.settings.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tobiasstrom.stairs.R
import com.tobiasstrom.stairs.common.service.DarkModeService
import com.tobiasstrom.stairs.common.service.DataStorePreferences
import com.tobiasstrom.stairs.ui.theme.ShortcutTheme

@Composable
fun Settings(
    viewModel: SettingsViewModel
) {
    val isInDarkMode by viewModel.isCurrentlyInDarkMode.collectAsState(false)
    val isDarkModeFollowSystem by viewModel.isCurrentlyDarkModeFollowSystem.collectAsState(false)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.stats_title)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_4x)))
        SwitchItem(
            text = stringResource(id = R.string.c_setDarkModeFollowSystem),
            function = { viewModel.toggleDarkModeFollowSystem() },
            value = isDarkModeFollowSystem
        )
        SwitchItem(
            text = stringResource(id = R.string.c_toggleDarkMode),
            function = { if (!isDarkModeFollowSystem) viewModel.toggleDarkMode() },
            value = isInDarkMode,
            enabled = !isDarkModeFollowSystem
        )
    }
}

@Composable
fun SwitchItem(
    text: String,
    function: () -> Unit,
    value: Boolean,
    enabled: Boolean = true
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(id = R.dimen.dimen_half),
                horizontal = dimensionResource(id = R.dimen.dimen_unit)
            ),
        onClick = function,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(vertical = dimensionResource(id = R.dimen.dimen_unit), horizontal = dimensionResource(id = R.dimen.dimen_2x)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text
            )
            Switch(
                checked = value,
                enabled = enabled,
                onCheckedChange = null
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ExampleCPreview() {
    ShortcutTheme {
        Settings(
            SettingsViewModel(
                DarkModeService(
                    LocalContext.current,
                    DataStorePreferences(LocalContext.current)
                )
            )
        )
    }
}