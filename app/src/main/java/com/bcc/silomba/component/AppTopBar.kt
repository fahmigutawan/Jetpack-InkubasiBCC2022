package com.bcc.silomba.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.bcc.silomba.ui.style.AppColor

@Composable
fun AppTopBar(
    onBackClicked: () -> Unit,
    midText: String
) {
    val density = LocalDensity.current
    val topBarHeight = remember { mutableStateOf(0.dp) }

    Box {
        TopAppBar(
            modifier = Modifier.onSizeChanged {
                with(density) {
                    topBarHeight.value = it.height.toDp()
                }
            },
            backgroundColor = AppColor.White,
        ) {}

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(topBarHeight.value),
            contentAlignment = Alignment.Center
        ) {
            // Back btn
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                IconButton(onClick = onBackClicked) {
                    Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back")
                }
            }

            // Title
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                AppText(text = midText, textType = AppTextType.Xl)
            }
        }
    }
}

@Composable
fun AppTopBar(
    midText: String
) {
    val density = LocalDensity.current
    val topBarHeight = remember { mutableStateOf(0.dp) }

    Box {
        TopAppBar(
            modifier = Modifier.onSizeChanged {
                with(density) {
                    topBarHeight.value = it.height.toDp()
                }
            },
            backgroundColor = AppColor.White,
        ) {}

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(topBarHeight.value),
            contentAlignment = Alignment.Center
        ) {
            AppText(text = midText, textType = AppTextType.Xl)
        }
    }
}