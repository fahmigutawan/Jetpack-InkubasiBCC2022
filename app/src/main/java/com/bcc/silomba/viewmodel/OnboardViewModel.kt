package com.bcc.silomba.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcc.brain.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
    private val repository: Repository
):ViewModel(){
    fun saveFirstTimeState(){
        viewModelScope.launch {
            repository.saveFirstTimeState(false)
        }
    }
}