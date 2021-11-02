package com.tobiasstrom.stairs.startup.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.*
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.*

@Composable
fun Splash(
    @Suppress("UNUSED_PARAMETER")
    viewModel: SplashViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "This is the splash screen")
        LottiSplashLotti()
    }
}

@Composable
fun LottiSplashLotti() {
    val compositionResult: LottieCompositionResult =
        rememberLottieComposition(spec = LottieCompositionSpec.Asset("/res/raw/splash_screen_lotti.json"))

    val progress by animateLottieCompositionAsState(
        compositionResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.0f
    )

    val color by derivedStateOf { Color.Red }

    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = color.toArgb(), keyPath = arrayOf(
                "compass needle",
                "Shape 1",
                "Fill 1"
            )
        ),

        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = color.toArgb(), keyPath = arrayOf(
                "donut",
                "Group 1",
                "Fill 1"
            )
        ),
        rememberLottieDynamicProperty(
            property = LottieProperty.OPACITY,
            value = 50,
            keyPath = arrayOf(
                "compass needle",
                "Shape 1",
                "Fill 1"
            )
        ),
    )
    LottieAnimation(
        compositionResult.value,
        progress,
        dynamicProperties = dynamicProperties,
        modifier = Modifier.padding(all = 50.dp)
    )
}
