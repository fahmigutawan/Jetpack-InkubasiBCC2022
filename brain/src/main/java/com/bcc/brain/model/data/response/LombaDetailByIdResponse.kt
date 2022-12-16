package com.bcc.brain.model.data.response

import kotlinx.serialization.Serializable

@Serializable
data class LombaDetailByIdResponse(
    val data:LombaDetailData,
    val success:Boolean,
    val message:String
)
