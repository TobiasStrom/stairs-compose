package com.tobiasstrom.stairs.tracking.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tobiasstrom.stairs.R

@Composable
fun TextInputField(
    infoText: String,
    inputValue: String,
    updateValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType,
    imeAction: ImeAction = ImeAction.Default
) {
    val focusManager = LocalFocusManager.current
    Card(
        modifier = modifier
            .fillMaxWidth(1f),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dimen_unit)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = infoText,
                modifier = Modifier
                    .fillMaxWidth(0.3f),
                textAlign = TextAlign.End
            )
            Text(
                text = ":",
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_quarter))
            )
            BasicTextField(
                value = inputValue,
                onValueChange = updateValue,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray)
                    .padding(vertical = 5.dp, horizontal = 16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    },
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        }
    }
}
/*
@Preview
@Composable
fun TextInputPreview() {
    Column(modifier = Modifier.fillMaxSize(1f)) {
        TextInputField(
            infoText = "Building",
            modifier = Modifier.padding(horizontal = 16.dp),
            keyboardType = KeyboardType.Ascii
        )
        Box(modifier = Modifier.height(8.dp))
        TextInputField(
            infoText = "Start floor",
            modifier = Modifier.padding(horizontal = 16.dp),
            keyboardType = KeyboardType.Number
        )
        Box(modifier = Modifier.height(8.dp))
        TextInputField(
            infoText = "End floor",
            modifier = Modifier.padding(horizontal = 16.dp),
            keyboardType = KeyboardType.Number
        )
    }
}

 */
