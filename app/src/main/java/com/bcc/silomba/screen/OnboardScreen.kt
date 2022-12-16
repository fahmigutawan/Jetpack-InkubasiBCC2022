package com.bcc.silomba.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.bcc.silomba.R
import com.bcc.silomba.component.AppButton
import com.bcc.silomba.component.AppText
import com.bcc.silomba.component.AppTextButton
import com.bcc.silomba.component.AppTextType
import com.bcc.silomba.ui.style.AppColor
import com.bcc.silomba.viewmodel.OnboardViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardScreen(
    onPassClicked: () -> Unit
) {
    /**Attrs*/
    val viewModel = hiltViewModel<OnboardViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val bottomTextHeight = LocalConfiguration.current.screenHeightDp / 2.5
    val topSectionHeight = LocalConfiguration.current.screenHeightDp - bottomTextHeight
    val pagerState = rememberPagerState()

    /**Function*/

    /**Content*/
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.Primary500)
    ) {
        HorizontalPager(
            count = OnboardScreenItem.values().size,
            state = pagerState
        ) { index ->
            Column() {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(topSectionHeight.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 64.dp),
                        model = OnboardScreenItem.values()[index].imgId,
                        contentDescription = "Img"
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(AppColor.White)
                ) {
                    Column(
                        modifier = Modifier
                            .heightIn(min = bottomTextHeight.dp)
                            .fillMaxWidth()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                HorizontalPagerIndicator(pagerState = pagerState)
                            }

                            AppText(
                                text = OnboardScreenItem.values()[index].title,
                                textType = AppTextType.XlSemibold,
                                color = AppColor.Primary500
                            )

                            AppText(
                                text = OnboardScreenItem.values()[index].description,
                                textType = AppTextType.Sm,
                                color = AppColor.Gray500
                            )
                        }

                        when {
                            OnboardScreenItem.values()[index]
                                    == OnboardScreenItem.values().last() -> {
                                AppButton(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        viewModel.saveFirstTimeState()
                                        onPassClicked()
                                    },
                                    text = "Ayo Mulai!"
                                )
                            }

                            else -> {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        AppTextButton(
                                            modifier = Modifier,
                                            onClick = {
                                                viewModel.saveFirstTimeState()
                                                onPassClicked()
                                            },
                                            text = "Lewati",
                                            textType = AppTextType.Md,
                                            color = AppColor.Primary500
                                        )

                                        AppButton(
                                            onClick = {
                                                coroutineScope.launch {
                                                    pagerState.animateScrollToPage(index + 1)
                                                }
                                            },
                                            text = "Berikutnya"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class OnboardScreenItem(
    val imgId: Int,
    val title: String,
    val description: String
) {
    Content1(
        R.drawable.img_onboard_content1,
        "Selamat datang di Genta",
        "Pusat informasi lomba terlengkap untuk membantumu menjadi generasi berprestasi masa depan."
    ),
    Content2(
        R.drawable.img_onboard_content2,
        "Temukan Partner Lomba Favoritmu!",
        "Tidak punya teman untuk mengikuti lomba? Jangan khawatir kamu bisa menemukan teman seperjuanganmu di sini."
    ),
    Content3(
        R.drawable.img_onboard_content3,
        "Jadilah Generasi Berprestasi Mulai dari Sekarang!",
        "Siapkah kamu menjadi generasi berprestasi? Tunggu apalagi? Mulai persiapkan dirimu dari sekarang!"
    )
}