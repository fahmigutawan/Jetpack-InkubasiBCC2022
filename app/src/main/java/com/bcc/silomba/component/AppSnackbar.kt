package com.bcc.silomba.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.bcc.silomba.ui.style.AppColor

@Composable
fun AppSnackbar(hostState: SnackbarHostState) {
    SnackbarHost(hostState = hostState) {
        Snackbar(
            snackbarData = it,
            shape = RoundedCornerShape(4.dp),
            backgroundColor = AppColor.Gray700,
            contentColor = AppColor.White,
            actionColor = AppColor.Primary500
        )
    }
}