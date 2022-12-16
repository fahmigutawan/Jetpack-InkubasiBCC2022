package com.bcc.brain.utils

import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> getResponse(
    httpCall: suspend () -> ApiResponse<T>
): Flow<ApiResponse<T>> = flow {
    try {
        emit(httpCall())
    }
    catch (e: RedirectResponseException) {
        // 3xx - responses
        emit(ApiResponse.Error("Error: ${e.response.status.description}"))
    }
    catch (e: ClientRequestException) {
        // 4xx - responses
        emit(ApiResponse.Error("Error: ${e.response.status.description}"))
    }
    catch (e: ServerResponseException) {
        // 5xx - responses
        emit(ApiResponse.Error("Error: ${e.response.status.description}"))
    }
    catch (e: Exception) {
        // 6xx - responses
        emit(ApiResponse.Error("Error: ${e.message}"))
    }
}.flowOn(Dispatchers.IO)