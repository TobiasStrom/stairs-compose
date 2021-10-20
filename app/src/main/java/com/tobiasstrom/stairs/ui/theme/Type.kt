package com.tobiasstrom.stairs.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val t = Typography()

val TextStyleHeadline1 = t.h5.copy(
    fontWeight = FontWeight.Bold,
    fontSize = 26.sp
)

val TextStyleHeadline2 = t.h6.copy(
    fontWeight = FontWeight.Bold
)

val TextStyleSubtitle = t.subtitle2.copy(
    fontSize = 16.sp
)

val TextStyleButton = t.button.copy(
    fontSize = 18.sp,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.sp
)

val TextStyleBody = t.body2

val TextStyleLink = t.body2.copy(
    fontSize = 15.sp,
    color = Primary
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = FontFamily.SansSerif,
    h1 = TextStyleHeadline1,
    h2 = TextStyleHeadline2,
    button = TextStyleButton,
    subtitle1 = TextStyleSubtitle,
    body1 = TextStyleBody
)
