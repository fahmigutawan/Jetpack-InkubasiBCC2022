package com.bcc.silomba.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.bcc.brain.model.presenter.LombaDetail
import com.bcc.brain.utils.Resource
import com.bcc.silomba.component.AppButton
import com.bcc.silomba.component.AppLoadingModifier
import com.bcc.silomba.component.AppText
import com.bcc.silomba.component.AppTextType
import com.bcc.silomba.component.AppTopBar
import com.bcc.silomba.ui.style.AppColor
import com.bcc.silomba.viewmodel.LombaDetailViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun LombaDetailScreen(
    onBackClicked: () -> Unit,
    onKlikDisiniClicked: (LombaDetail) -> Unit,
    lombaDetail: LombaDetail
) {
    /**Attrs*/
    val viewModel = hiltViewModel<LombaDetailViewModel>()
    val isLoading = remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading.value)
    val scrWidth = LocalConfiguration.current.screenWidthDp
    val myLombaDetail = viewModel.lombaDetail.collectAsState()

    /**Function*/
    LaunchedEffect(key1 = true) {
        if (viewModel.lombaDetail.value is Resource.Loading) {
            delay(2500)
            viewModel.lombaDetail.value = Resource.Success(lombaDetail)
        }
    }

    /**Content*/
    Scaffold(
        topBar = {
            AppTopBar(
                onBackClicked = onBackClicked,
                midText = "Detail Lomba"
            )
        }
    ) {
        SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.refresh(lombaDetail) }) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                //Spacer 16dp
                item { Spacer(modifier = Modifier.height(16.dp)) }

                //Image
                item {
                    when (myLombaDetail.value) {
                        is Resource.Error -> {}
                        is Resource.Loading -> {
                            Box(
                                modifier = AppLoadingModifier
                                    .rectLoadingModifier
                                    .fillMaxWidth()
                                    .height(scrWidth.dp)
                                    .background(AppColor.Gray400),
                            )
                        }

                        is Resource.Success -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(scrWidth.dp)
                                    .background(AppColor.Gray400),
                                contentAlignment = Alignment.Center
                            ) {
                                AsyncImage(
                                    contentScale = ContentScale.FillBounds,
                                    model = myLombaDetail.value.data!!.gambar,
                                    contentDescription = "Image"
                                )
                            }
                        }
                    }
                }

                //Spacer 16dp
                item { Spacer(modifier = Modifier.height(16.dp)) }

                //Lomba title
                item {
                    when (myLombaDetail.value) {
                        is Resource.Error -> {}
                        is Resource.Loading -> {
                            AppText(
                                text = "This is sample of loading competition",
                                textType = AppTextType.Xs,
                                modifier = AppLoadingModifier.textLoadingModifier
                            )
                        }

                        is Resource.Success -> {
                            AppText(
                                text = viewModel.lombaDetail.value.data!!.nama_lomba,
                                textType = AppTextType.LgSemibold
                            )
                        }
                    }
                }

                //Spacer 4dp
                item { Spacer(modifier = Modifier.height(4.dp)) }

                //Universitas penyelenggara
                item {
                    when (myLombaDetail.value) {
                        is Resource.Error -> {}
                        is Resource.Loading -> {
                            AppText(
                                text = "competition holder",
                                textType = AppTextType.Xs,
                                modifier = AppLoadingModifier.textLoadingModifier
                            )
                        }

                        is Resource.Success -> {
                            AppText(
                                text = myLombaDetail.value.data!!.universitas,
                                textType = AppTextType.MdSemibold,
                                color = AppColor.Primary500
                            )
                        }
                    }
                }

                //Spacer 16dp
                item { Spacer(modifier = Modifier.height(16.dp)) }

                //Bidang
                item {
                    Column {
                        AppText(text = "Bidang", textType = AppTextType.XsSemibold)
                        when (myLombaDetail.value) {
                            is Resource.Error -> {}
                            is Resource.Loading -> {
                                AppText(
                                    text = "sample",
                                    textType = AppTextType.Xs,
                                    modifier = AppLoadingModifier.textLoadingModifier
                                )
                            }

                            is Resource.Success -> {
                                AppText(
                                    text = myLombaDetail.value.data!!.sub_kategori,
                                    textType = AppTextType.XsSemibold,
                                    color = AppColor.Primary500
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(AppColor.Gray400)
                        )
                    }
                }

                //Spacer 16dp
                item { Spacer(modifier = Modifier.height(16.dp)) }

                //Deadline
                item {
                    Column {
                        AppText(text = "Deadline", textType = AppTextType.XsSemibold)
                        when (myLombaDetail.value) {
                            is Resource.Error -> {}
                            is Resource.Loading -> {
                                AppText(
                                    text = "12-12-2000",
                                    textType = AppTextType.Xs,
                                    modifier = AppLoadingModifier.textLoadingModifier
                                )
                            }

                            is Resource.Success -> {
                                AppText(
                                    text = myLombaDetail.value.data!!.deadline_daftar,
                                    textType = AppTextType.XsSemibold,
                                    color = AppColor.Primary500
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(AppColor.Gray400)
                        )
                    }
                }

                //Spacer 16dp
                item { Spacer(modifier = Modifier.height(16.dp)) }

                //Pengumuman
                item {
                    Column {
                        AppText(text = "Pengumuman", textType = AppTextType.XsSemibold)
                        when (myLombaDetail.value) {
                            is Resource.Error -> {}
                            is Resource.Loading -> {
                                AppText(
                                    text = "12-12-2000",
                                    textType = AppTextType.Xs,
                                    modifier = AppLoadingModifier.textLoadingModifier
                                )
                            }

                            is Resource.Success -> {
                                AppText(
                                    text = myLombaDetail.value.data!!.tgl_pengumuman,
                                    textType = AppTextType.XsSemibold,
                                    color = AppColor.Primary500
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(AppColor.Gray400)
                        )
                    }
                }

                //Spacer 16dp
                item { Spacer(modifier = Modifier.height(16.dp)) }

                //Biaya pendaftaran
                item {
                    Column {
                        AppText(text = "Biaya Pendaftaran", textType = AppTextType.XsSemibold)
                        when (myLombaDetail.value) {
                            is Resource.Error -> {}
                            is Resource.Loading -> {
                                AppText(
                                    text = "12-12-2000",
                                    textType = AppTextType.Xs,
                                    modifier = AppLoadingModifier.textLoadingModifier
                                )
                            }

                            is Resource.Success -> {
                                AppText(
                                    text = "Rp ${myLombaDetail.value.data!!.biaya_daftar}",
                                    textType = AppTextType.XsSemibold,
                                    color = AppColor.Primary500
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(AppColor.Gray400)
                        )
                    }
                }

                //Spacer 16dp
                item { Spacer(modifier = Modifier.height(16.dp)) }

                //Deskripsi
                item {
                    Column {
                        AppText(text = "Deskripsi", textType = AppTextType.XsSemibold)
                        when (myLombaDetail.value) {
                            is Resource.Error -> {}
                            is Resource.Loading -> {
                                AppText(
                                    text = "Lorem ipsum Bla bla bla...",
                                    textType = AppTextType.Xs,
                                    modifier = AppLoadingModifier.textLoadingModifier
                                )
                            }

                            is Resource.Success -> {
                                AppText(
                                    text = myLombaDetail.value.data!!.deskripsi,
                                    textType = AppTextType.Xs,
                                    color = AppColor.Gray500
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(AppColor.Gray400)
                        )
                    }
                }

                //Spacer 16dp
                item { Spacer(modifier = Modifier.height(16.dp)) }

                //Deskripsi
                item {
                    AppButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onKlikDisiniClicked(lombaDetail) },
                        text = "Informasi lebih lanjut klik di sini"
                    )
                }

                //Spacer 16dp
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}