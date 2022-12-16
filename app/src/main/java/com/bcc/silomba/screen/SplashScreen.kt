package com.bcc.silomba.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.bcc.silomba.R
import com.bcc.silomba.ui.style.AppColor
import com.bcc.silomba.viewmodel.SplashScreenViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToOnboard: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    /**Attrs*/
    val logoSize = LocalConfiguration.current.screenWidthDp / 2
    val creditSize = LocalConfiguration.current.screenWidthDp / 3
    val viewModel = hiltViewModel<SplashScreenViewModel>()

    /**Function*/
    LaunchedEffect(key1 = true) {
        delay(2500)
        viewModel.getFirstTimeOpenAppState { firstTime ->
            when (firstTime) {
                true -> onNavigateToOnboard()
                false -> {
                    viewModel.getToken { token ->
                        when (token) {
                            "" -> onNavigateToLogin()
                            else -> onNavigateToHome()
                        }
                    }
                }
            }
        }
    }

    /**Content*/
    Box(modifier = Modifier.background(AppColor.White)) {
        // Bcc Logo
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            AsyncImage(
                modifier = Modifier.width(creditSize.dp).padding(bottom = 64.dp),
                model = R.drawable.img_presented_by_bcc,
                contentDescription = "Presented by BCC"
            )
        }

        // Logo
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AsyncImage(
                modifier = Modifier.width(logoSize.dp),
                model = R.drawable.ic_circle,
                contentDescription = "Presented by BCC"
            )
        }
    }
}