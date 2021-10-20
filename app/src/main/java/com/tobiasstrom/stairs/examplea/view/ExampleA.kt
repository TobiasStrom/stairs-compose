package com.tobiasstrom.stairs.examplea.view

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
fun ExampleA(
    viewModel: AViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val navManager = get<NavigationManager>()

        ShortcutButton(onClick = { viewModel.navigateToB() }) {
            Text(stringResource(R.string.a_navigate))
        }

        Spacer(Modifier.height(dimensionResource(R.dimen.dimen_3x)))

        ShortcutButton(onClick = {
            navManager.showBottomSheet {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.dimen_2x)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "This is some content added to the ModalBottomSheet!",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(dimensionResource(R.dimen.dimen_2x)))
                    Text(
                        text = "Press the button below, tap outside the ModalBottomSheet, drag the ModalBottomSheet down towards the bottom, or press the system's back button/gesture to close this ModalBottomSheet",
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(dimensionResource(R.dimen.dimen_4x)))
                    ShortcutButton(onClick = { navManager.hideBottomSheet() }) {
                        Text("Close ModalBottomSheet")
                    }
                }
            }
        }) {
            Text("ModalBottomSheet example")
        }

        Spacer(Modifier.height(dimensionResource(R.dimen.dimen_3x)))

        ShortcutButton(onClick = {
            navManager.showSnackbarMessage("This is a snackbar message!") {
                navManager.showSnackbarMessage("You pressed \"OK\" :)")
            }
        }) {
            Text("Snackbar w/callback")
        }
    }
}
