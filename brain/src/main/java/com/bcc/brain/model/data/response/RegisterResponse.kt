package com.bcc.brain.model.data.response

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val data:RegisterResponseData,
    val message:String,
    val success:Boolean
)

@Serializable
data class RegisterResponseData(
    val ID:Int,
    val CreatedAt:String,
    val UpdatedAt:String,
    val nama:String,
    val email:String,
    val password:String
)
