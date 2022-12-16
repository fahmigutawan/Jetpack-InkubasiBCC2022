package com.bcc.silomba.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bcc.brain.data.repository.Repository
import com.bcc.brain.model.presenter.LombaDetail
import com.bcc.brain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LombaDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val lombaDetail = MutableStateFlow<Resource<LombaDetail>>(Resource.Loading())

    fun getLombaDetailById(lomba_id:String){

    }

    fun refresh(lombaDetail: LombaDetail){
        getLombaDetailById(lombaDetail.id)
    }
}