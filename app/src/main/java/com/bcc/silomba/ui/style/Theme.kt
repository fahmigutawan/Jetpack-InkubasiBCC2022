package com.bcc.silomba.ui.style

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

fun lightColorScheme() = lightColors(
    primary = AppColor.Primary500,
//    primaryVariant =,
//    secondary =,
//    secondaryVariant =,
//    background =,
//    surface =,
//    error =,
//    onPrimary =,
//    onSecondary =,
//    onBackground =,
//    onSurface =,
//    onError =
)

@Composable
fun SiLombaTheme(
    content:@Composable () -> Unit
) {
    MaterialTheme(
        colors = lightColorScheme(),
        content = content
    )
}