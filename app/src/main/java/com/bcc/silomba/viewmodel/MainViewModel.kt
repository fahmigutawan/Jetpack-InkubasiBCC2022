package com.bcc.silomba.viewmodel

import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.bcc.brain.data.repository.Repository
import com.bcc.brain.model.presenter.LombaDetail
import com.bcc.silomba.component.AppBottomMenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) :ViewModel() {
    val showBottomBar = mutableStateOf(false)
    val bottomMenuState = mutableStateOf(AppBottomMenuItem.Home)
    val snackbarMessage = mutableStateOf("")
    val snackbarAction = mutableStateOf({})
    val snackbarActionLabel = mutableStateOf("Tutup")
    val snackbarActive = mutableStateOf(false)

    val pickedLomba = mutableStateOf<LombaDetail?>(null)
}