package com.bcc.brain.data.remote

import androidx.datastore.preferences.protobuf.Api
import com.bcc.brain.data.ktor.ApiServices
import com.bcc.brain.model.data.request.LoginRequest
import com.bcc.brain.model.data.request.RegisterRequest
import com.bcc.brain.model.data.response.LoginResponse
import com.bcc.brain.utils.ApiResponse
import com.bcc.brain.utils.getResponse
import io.ktor.client.*
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiServices: ApiServices
) {
    fun login(email:String, password:String): Flow<ApiResponse<LoginResponse>> = getResponse{
        val response = apiServices.login(LoginRequest(email, password))

        if(response.success){
            ApiResponse.Success(response)
        }else{
            ApiResponse.Error(response.message)
        }
    }

    fun register(email:String, password: String, confirm_password:String, nama:String) = getResponse {
        val response = apiServices.register(
            RegisterRequest(email, password, confirm_password, nama)
        )

        if(response.success){
            ApiResponse.Success(response)
        }else{
            ApiResponse.Error(response.message)
        }
    }

    fun getAllLomba() = getResponse {
        val response = apiServices.getAllLomba()

        if(response.success){
            ApiResponse.Success(response)
        }else{
            ApiResponse.Error(response.message)
        }
    }

    fun getLombaById(id:String) = getResponse {
        val response = apiServices.getLombaById(id)

        if(response.success){
            ApiResponse.Success(response)
        }else{
            ApiResponse.Error(response.message)
        }
    }
}