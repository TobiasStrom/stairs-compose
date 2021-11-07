package com.tobiasstrom.stairs.tracking.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tobiasstrom.stairs.tracking.model.Time

@Composable
fun TimerClockLap(time: Time, modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = time.min,
            fontSize = 30.sp
        )
        Text(
            text = ":",
            fontSize = 30.sp
        )
        Text(
            text = time.sec,
            fontSize = 30.sp
        )
        Text(
            text = ":",
            fontSize = 30.sp
        )
        Text(
            text = time.millis,
            fontSize = 30.sp
        )
    }
}

@Composable
fun TimerClock(time: Time, modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = time.min,
            fontSize = 40.sp
        )
        Text(
            text = ":",
            fontSize = 40.sp
        )
        Text(
            text = time.sec,
            fontSize = 40.sp
        )
        Text(
            text = ":",
            fontSize = 40.sp
        )
        Text(
            text = time.millis,
            fontSize = 40.sp
        )


    }
}

@Preview
@Composable
fun PreviewComposable() {
    Column() {
        TimerClock(time = Time("09", "20", "45"), modifier = Modifier.padding(1.dp))
        TimerClockLap(time = Time("09", "20", "45"), modifier = Modifier.padding(1.dp))
    }

}
