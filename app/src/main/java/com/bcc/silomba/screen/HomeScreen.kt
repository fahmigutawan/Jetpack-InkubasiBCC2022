package com.bcc.silomba.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bcc.brain.utils.MapperDomainToPresenter
import com.bcc.brain.utils.Resource
import com.bcc.silomba.component.AppText
import com.bcc.silomba.component.AppTextButton
import com.bcc.silomba.component.AppTextType
import com.bcc.silomba.component.LombaItem
import com.bcc.silomba.ui.style.AppColor
import com.bcc.silomba.viewmodel.HomeViewModel
import com.bcc.silomba.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    onNavigateToDetailLomba: () -> Unit
) {
    /**Attrs*/
    val viewModel = hiltViewModel<HomeViewModel>()
    val allLomba = viewModel.allLomba.collectAsState()

    /**Function*/

    /**Content*/
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = AppColor.White, contentColor = Color.Unspecified) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AppText(text = "Judul", textType = AppTextType.Xl)
                    Icon(
                        modifier = Modifier.clickable { },
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notif"
                    )
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            //Daftar pantau title
            item {
                AppText(text = "Daftar Pantau", textType = AppTextType.XlSemibold)
            }

            //TODO Daftar pantau item bellow here

            //Daftar lomba title
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AppText(text = "Daftar Lomba", textType = AppTextType.XlSemibold)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AppTextButton(
                            text = "Edit",
                            onClick = { /*TODO*/ },
                            textType = AppTextType.XsSemibold,
                            color = AppColor.Error900
                        )

                        AppTextButton(
                            text = "Lihat Semua",
                            onClick = { /*TODO*/ },
                            textType = AppTextType.XsSemibold,
                            color = AppColor.Primary500
                        )
                    }
                }
            }

            when (allLomba.value) {
                is Resource.Error -> {

                }

                is Resource.Loading -> {
                    /*TODO*/
                }

                is Resource.Success -> {
                    items(allLomba.value.data!!) {
                        LombaItem(
                            lomba = MapperDomainToPresenter.mapLombaDetail(it),
                            onClick = {
                                mainViewModel.pickedLomba.value =
                                    MapperDomainToPresenter.mapLombaDetail(it)
                                onNavigateToDetailLomba()
                            }
                        )
                    }
                }
            }
        }
    }
}