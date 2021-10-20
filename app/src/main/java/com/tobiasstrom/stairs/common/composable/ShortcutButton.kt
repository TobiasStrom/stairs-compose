package com.tobiasstrom.stairs.common.composable

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tobiasstrom.stairs.R
import com.tobiasstrom.stairs.ui.theme.ShortcutTheme

// This is an example on how to override certain styles of often-used components.
// Here, we do some minor changes to the padding- and size defaults of a normal button.

@Composable
fun ShortcutButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = PaddingValues(
        top = dimensionResource(R.dimen.dimen_unit),
        bottom = dimensionResource(R.dimen.dimen_unit),
        start = dimensionResource(R.dimen.dimen_3x),
        end = dimensionResource(R.dimen.dimen_3x)
    ),
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier
            .defaultMinSize(
                minWidth = 176.dp,
                minHeight = dimensionResource(R.dimen.dimen_6x)
            ),
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = content
    )
}

@Preview(name = "light", showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview(name = "dark", showBackground = true, backgroundColor = 0xFF000000, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ShortcutButtonPreview() {
    ShortcutTheme {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShortcutButton(onClick = { /*TODO*/ }) {
                Text(stringResource(R.string.app_name))
            }

            Spacer(Modifier.height(dimensionResource(R.dimen.dimen_2x)))

            ShortcutButton(onClick = { /*TODO*/ }) {
                Text("Button")
            }
        }
    }
}
