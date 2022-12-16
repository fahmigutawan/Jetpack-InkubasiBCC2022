package com.bcc.silomba.screen

import android.widget.Space
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bcc.silomba.component.AppText
import com.bcc.silomba.component.AppTextInputNormal
import com.bcc.silomba.component.AppTextType
import com.bcc.silomba.component.AppTopBar
import com.bcc.silomba.viewmodel.CariLombaViewModel

@Composable
fun CariLombaScreen() {
    /**Attrs*/
    val viewModel = hiltViewModel<CariLombaViewModel>()

    /**Function*/

    /**Content*/
    Scaffold(
        topBar = {
            AppTopBar(
                midText = "Cari Lomba"
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            //Top padding
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            //Search field
            item {
                AppTextInputNormal(
                    modifier = Modifier.fillMaxWidth(),
                    placeHolder = "Cari lomba kamu (misal Biologi)",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    value = viewModel.searchValue.value,
                    onValueChange = {
                        viewModel.searchValue.value = it
                    }
                )
            }

            //Kategori title
            item {
                AppText(text = "Daftar Pantau", textType = AppTextType.XlSemibold)
            }

            //Bottom
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}