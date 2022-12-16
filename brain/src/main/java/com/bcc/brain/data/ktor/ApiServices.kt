package com.bcc.brain.data.ktor

import com.bcc.brain.model.data.request.LoginRequest
import com.bcc.brain.model.data.request.RegisterRequest
import com.bcc.brain.model.data.response.LombaDetailData
import com.bcc.brain.model.data.response.AllLombaDetailResponse
import com.bcc.brain.model.data.response.LoginResponse
import com.bcc.brain.model.data.response.LombaDetailByIdResponse
import com.bcc.brain.model.data.response.RegisterResponse

interface ApiServices {
    suspend fun login(request:LoginRequest):LoginResponse

    suspend fun register(request: RegisterRequest):RegisterResponse

    suspend fun getAllLomba():AllLombaDetailResponse

    suspend fun getLombaById(id:String):LombaDetailByIdResponse
}