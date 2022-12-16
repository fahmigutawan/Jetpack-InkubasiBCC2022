package com.bcc.silomba.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcc.brain.data.repository.Repository
import com.bcc.brain.model.domain.LombaDetailDomain
import com.bcc.brain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) :ViewModel() {
    val allLomba = MutableStateFlow<Resource<List<LombaDetailDomain>>>(Resource.Loading())

    fun getAllLomba(){
        viewModelScope.launch {
            repository.getAllLomba().collect{
                allLomba.value = it
            }
        }
    }

    init {
        getAllLomba()
    }
}