package com.tobiasstrom.stairs.tracking.trackingstats.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.tobiasstrom.stairs.R
import com.tobiasstrom.stairs.common.composable.ShortcutButton
import com.tobiasstrom.stairs.common.composable.SwitchItem
import com.tobiasstrom.stairs.tracking.components.TextInputField

@Composable
fun TrackingStats(
    @Suppress("UNUSED_PARAMETER")
    viewModel: TrackingStatsViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_2x)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Edit your activity", fontSize = 30.sp
            )
            TextInputField(
                "Building",
                inputValue = viewModel.buildingName,
                updateValue = viewModel.updateBuildingName,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_unit)),
                keyboardType = KeyboardType.Ascii,
                imeAction = ImeAction.Next
            )

            TextInputField(
                "Start floor",
                inputValue = viewModel.startFloor.toString(),
                updateValue = viewModel.updateStartFloor,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_unit)),
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
            TextInputField(
                "End Floor",
                inputValue = viewModel.endFloor.toString(),
                updateValue = viewModel.updateEndFloor,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_unit)),
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
            SwitchItem(text = "Has First flor", function = { /*TODO*/ }, value = false, modifier = Modifier.padding(bottom = dimensionResource(
                id = R.dimen.dimen_unit
            )))
            TextInputField(
                "Steps each floor",
                inputValue = viewModel.endFloor.toString(),
                updateValue = viewModel.updateEndFloor,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_unit)),
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
            TextInputField(
                "Height each floor",
                inputValue = viewModel.endFloor.toString(),
                updateValue = viewModel.updateEndFloor,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        }
        Column(modifier = Modifier.fillMaxWidth(1f),
        horizontalAlignment = Alignment.CenterHorizontally) {
            ShortcutButton(
                onClick = { /*TODO*/ }, modifier = Modifier.padding(
                    bottom = dimensionResource(
                        id = R.dimen.dimen_unit
                    )
                )
            ) {
                Text(text = "Load last values")
            }
            ShortcutButton(
                onClick = {viewModel.saveActuvuty() }, modifier = Modifier.padding(
                    bottom = dimensionResource(
                        id = R.dimen.dimen_unit
                    )
                )
            ) {
                Text(text = "Save Activity")
            }
        }

    }

}

