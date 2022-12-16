package com.bcc.brain.model.data.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email:String,
    val password:String,
    val confirm_password:String,
    val nama:String
)
