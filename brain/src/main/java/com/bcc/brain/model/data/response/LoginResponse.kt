package com.bcc.brain.model.data.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val data:String,
    val message:String,
    val success:Boolean,
    val token:String
)
