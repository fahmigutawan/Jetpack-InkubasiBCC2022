package com.bcc.silomba.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.IconButton
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bcc.silomba.R
import com.bcc.silomba.navigation.AppNavRoute
import com.bcc.silomba.ui.style.AppColor

@Composable
fun AppBottomMenu(
    bottomMenuState: MutableState<AppBottomMenuItem>,
    onItemClicked: (route: String) -> Unit
) {
    BottomAppBar(
        backgroundColor = AppColor.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AppBottomMenuItem
                .values()
                .forEach { item ->
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(color = AppColor.Black),
                                onClick = {
                                    bottomMenuState.value = item
                                    onItemClicked(item.route)
                                }
                            )
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp),
                            model = when (bottomMenuState.value) {
                                item -> item.iconIdSelected
                                else -> item.iconIdUnselected
                            },
                            contentDescription = "Icon"
                        )
                    }
                }
        }
    }
}

enum class AppBottomMenuItem(
    val title: String,
    val iconIdSelected: Int,
    val iconIdUnselected: Int,
    val route: String
) {
    Home(
        "Beranda",
        R.drawable.ic_botmenu_home_selected,
        R.drawable.ic_botmenu_home_unselected,
        AppNavRoute.HomeScreen.name
    ),
    Lomba(
        "Lomba",
        R.drawable.ic_botmenu_lomba_selected,
        R.drawable.ic_botmenu_lomba_unselected,
        AppNavRoute.LombaScreen.name
    ),
    Partner(
        "Partner",
        R.drawable.ic_botmenu_partner_selected,
        R.drawable.ic_botmenu_partner_unselected,
        AppNavRoute.PartnerScreen.name
    ),
    Layanan(
        "Layanan",
        R.drawable.ic_botmenu_layanan_selected,
        R.drawable.ic_botmenu_layanan_unselected,
        AppNavRoute.LayananScreen.name
    ),
    Profil(
        "Profil",
        R.drawable.ic_botmenu_profil_selected,
        R.drawable.ic_botmenu_profil_unselected,
        AppNavRoute.ProfilScreen.name
    )
}