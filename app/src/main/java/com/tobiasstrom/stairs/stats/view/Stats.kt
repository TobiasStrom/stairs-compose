package com.tobiasstrom.stairs.stats.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tobiasstrom.stairs.R
import com.tobiasstrom.stairs.ui.theme.ShortcutTheme

@Composable
fun Stats(
    viewModel: StatsViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.stats_title)
        )
        LazyColumn{
            items(viewModel.items){ names ->
                Text(text = names)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ExampleCPreview() {
    ShortcutTheme {
        Stats(
            StatsViewModel(

            )
        )
    }
}