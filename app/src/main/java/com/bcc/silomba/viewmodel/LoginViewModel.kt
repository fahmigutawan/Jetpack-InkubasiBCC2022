package com.bcc.silomba.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcc.brain.data.repository.Repository
import com.bcc.brain.model.data.response.LoginResponse
import com.bcc.brain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val emailState = mutableStateOf("")
    val firstEmailState = mutableStateOf(true)
    val passwordState = mutableStateOf("")
    val firstPasswordState = mutableStateOf(true)
    val isEmailValid = derivedStateOf {
        emailState.value.contains("@")
                || firstEmailState.value
    }
    val isEmailNotEmpty = derivedStateOf {
        emailState.value.isNotEmpty()
                || firstEmailState.value
    }
    val isPasswordValid = derivedStateOf {
        passwordState.value.length >= 6
                || firstPasswordState.value
    }
    val isPasswordNotEmpty = derivedStateOf {
        passwordState.value.isNotEmpty()
                || firstPasswordState.value
    }

    private val _loginResponse = MutableStateFlow<Resource<LoginResponse>>(Resource.Loading())
    val loginResponse get() = _loginResponse

    fun login() {
        viewModelScope.launch {
            repository.login(emailState.value, passwordState.value).collect {
                _loginResponse.value = it
            }
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            repository.saveToken(token)
        }
    }
}