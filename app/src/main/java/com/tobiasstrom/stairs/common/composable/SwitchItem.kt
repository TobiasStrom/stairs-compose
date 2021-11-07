package com.tobiasstrom.stairs.common.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.tobiasstrom.stairs.R

@Composable
fun SwitchItem(
    text: String,
    modifier: Modifier = Modifier,
    function: () -> Unit,
    value: Boolean,
    enabled: Boolean = true
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
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