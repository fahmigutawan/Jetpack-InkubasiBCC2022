package com.bcc.silomba.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bcc.brain.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CariLombaViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val searchValue = mutableStateOf("")
}