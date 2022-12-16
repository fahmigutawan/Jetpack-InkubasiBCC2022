package com.bcc.silomba.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bcc.brain.model.presenter.LombaDetail
import com.bcc.silomba.ui.style.AppColor

@Composable
fun LombaItem(
    lomba: LombaDetail,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    itemHeight: Dp = 100.dp
) {
    val density = LocalDensity.current
    val imgWidth = LocalConfiguration.current.screenWidthDp / 3
    val boxWidth = remember { mutableStateOf(0.dp) }

    Column(
        modifier = modifier
            .onSizeChanged {
                with(density) {
                    boxWidth.value = it.width.toDp()
                }
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = AppColor.Black),
                onClick = { onClick() }
            )
    ) {
        // Item
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            //Img
            Box(
                modifier = Modifier
                    .size(width = imgWidth.dp, height = itemHeight)
                    .background(AppColor.Gray300),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(model = lomba.gambar, contentDescription = "Gambar")
            }

            //Informations
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                // Tanggal & Kategori
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AppText(
                        text = "Tanggal",
                        textType = AppTextType.Xs,
                        color = AppColor.Success700
                    )

                    AppText(text = lomba.nama_kategori, textType = AppTextType.Xs)
                }

                // Nama lomba
                AppText(text = lomba.nama_lomba, textType = AppTextType.SmSemibold)

                // Penyelenggara
                AppText(
                    text = lomba.universitas,
                    textType = AppTextType.Sm,
                    color = AppColor.Gray500
                )

                // Biaya & sisa waktu
                AppText(
                    text = "Rp ${lomba.biaya_daftar}",
                    textType = AppTextType.SmSemibold,
                    color = AppColor.Primary500
                )
            }
        }

        // Bottom divider
        Box(
            modifier = Modifier
                .height(1.dp)
                .width(boxWidth.value)
                .background(AppColor.Gray300)
        )
    }
}