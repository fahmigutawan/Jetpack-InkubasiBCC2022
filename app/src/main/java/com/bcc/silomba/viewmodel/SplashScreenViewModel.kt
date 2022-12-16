package com.bcc.silomba.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcc.brain.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    fun getToken(onRetrieved:(String) -> Unit){
        viewModelScope.launch {
            repository.getToken().collect{
                onRetrieved(it)
            }
        }
    }

    fun getFirstTimeOpenAppState(onRetrieved: (Boolean) -> Unit){
        viewModelScope.launch {
            repository.getFirstTimeState().collect{
                onRetrieved(it)
            }
        }
    }
}