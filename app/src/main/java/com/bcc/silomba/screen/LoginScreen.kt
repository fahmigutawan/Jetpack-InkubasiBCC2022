package com.bcc.silomba.screen

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.bcc.brain.utils.Resource
import com.bcc.silomba.R
import com.bcc.silomba.component.AppButton
import com.bcc.silomba.component.AppText
import com.bcc.silomba.component.AppTextButton
import com.bcc.silomba.component.AppTextInputNormal
import com.bcc.silomba.component.AppTextType
import com.bcc.silomba.ui.style.AppColor
import com.bcc.silomba.viewmodel.LoginViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    onLupaPasswordClicked: () -> Unit,
    onNavigateToHome: () -> Unit,
    showSnackbar: (message: String) -> Unit
) {
    /**Attrs*/
    val viewModel = hiltViewModel<LoginViewModel>()
    val logoSize = LocalConfiguration.current.screenWidthDp / 2.5
    val isEmailValid = viewModel.isEmailValid
    val isEmailNotEmpty = viewModel.isEmailNotEmpty
    val isPasswordValid = viewModel.isPasswordValid
    val isPasswordNotEmpty = viewModel.isPasswordNotEmpty
    val loginResponse = viewModel.loginResponse.collectAsState()
    val isLoading = remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading.value)

    /**Function*/
    when (loginResponse.value) {
        is Resource.Error -> {
            isLoading.value = false
            LaunchedEffect(key1 = true) {
                showSnackbar(loginResponse.value.message ?: "Login gagal, cek koneksi anda")
            }
        }

        is Resource.Loading -> {}
        is Resource.Success -> {
            isLoading.value = false
            LaunchedEffect(key1 = true) {
                showSnackbar("Login berhasil")
                viewModel.saveToken(loginResponse.value.data!!.token)
                delay(2000)
                onNavigateToHome()
            }
        }
    }

    /**Content*/
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {/*TODO*/ }
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            //Title
            item {
                Column {
                    AppText(text = "Selamat Datang", textType = AppTextType.LgSemibold)
                    AppText(
                        text = "Masuk untuk menjelajah lebih jauh",
                        textType = AppTextType.Xs,
                        color = AppColor.Gray500
                    )
                }
            }

            //Logo
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    AsyncImage(
                        modifier = Modifier.width(logoSize.dp),
                        model = R.drawable.img_login,
                        contentDescription = "Login"
                    )
                }
            }

            //Input fields
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    //Email
                    AppTextInputNormal(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Email",
                        placeHolder = "Enter email",
                        value = viewModel.emailState.value,
                        onValueChange = {
                            viewModel.firstEmailState.value = false
                            viewModel.emailState.value = it
                        },
                        showWarningMessage = !isEmailValid.value || !isEmailNotEmpty.value,
                        warningMessage = "Email tidak valid",
                        isError = !isEmailValid.value || !isEmailNotEmpty.value
                    )

                    //Password
                    AppTextInputNormal(
                        modifier = Modifier.fillMaxWidth(),
                        placeHolder = "Enter password",
                        label = "Password",
                        value = viewModel.passwordState.value,
                        onValueChange = {
                            viewModel.passwordState.value = it
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        showWarningMessage = !isPasswordValid.value || !isPasswordNotEmpty.value,
                        warningMessage = "Password tidak valid",
                        isError = !isPasswordValid.value || !isPasswordNotEmpty.value
                    )

                    //Lupa password
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        AppTextButton(
                            text = "Lupa Password?",
                            onClick = onLupaPasswordClicked,
                            textType = AppTextType.XsSemibold,
                            color = AppColor.Primary500
                        )
                    }
                }
            }

            //Login buttons
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    //Normal login
                    AppButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            isLoading.value = true
                            viewModel.login()
                        },
                        text = "Login"
                    )

                    //Ord
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        AppText(
                            text = "OR",
                            textType = AppTextType.XsSemibold,
                            color = AppColor.Gray500
                        )
                    }

                    //Google login
                    AppButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { /*TODO*/ },
                        backgroundColor = AppColor.Gray300,
                        rippleColor = AppColor.Black
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = rememberAsyncImagePainter(model = R.drawable.ic_google),
                                contentDescription = "Icon",
                                tint = Color.Unspecified
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            AppText(
                                text = "Login with Google",
                                textType = AppTextType.Lg,
                                color = AppColor.Black
                            )
                        }
                    }
                }
            }

            //Belum memiliki akun
            item {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AppText(
                        text = "Belum memiliki akun?",
                        textType = AppTextType.SmSemibold,
                        color = AppColor.Black
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    AppTextButton(
                        onClick = {
                            /*TODO*/
                        },
                        text = "Daftar",
                        textType = AppTextType.SmSemibold,
                        color = AppColor.Primary500
                    )
                }
            }
        }
    }
}