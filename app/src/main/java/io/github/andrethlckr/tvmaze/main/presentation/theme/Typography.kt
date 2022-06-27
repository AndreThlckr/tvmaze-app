package io.github.andrethlckr.tvmaze.main.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    h1 = TextStyle(
        fontSize = 30.sp,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.SansSerif
    ),
    h3 = TextStyle(
        fontSize = 24.sp,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.SansSerif
    ),
    body1 = TextStyle(
        fontSize = 18.sp,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.SansSerif
    ),
    body2 = TextStyle(
        fontSize = 16.sp,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.SansSerif
    )
)
