package com.bcc.brain.data.ktor

import com.bcc.brain.data.datastore.DatastoreSource
import com.bcc.brain.model.data.request.LoginRequest
import com.bcc.brain.model.data.request.RegisterRequest
import com.bcc.brain.model.data.response.LombaDetailData
import com.bcc.brain.model.data.response.AllLombaDetailResponse
import com.bcc.brain.model.data.response.LoginResponse
import com.bcc.brain.model.data.response.LombaDetailByIdResponse
import com.bcc.brain.model.data.response.RegisterResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.first

class ApiServicesImpl(
    private val client: HttpClient,
    private val datastoreSource: DatastoreSource
    ) :ApiServices {
    override suspend fun login(request: LoginRequest): LoginResponse =
        client.post {
            url(HttpRoutes.LOGIN)
            contentType(ContentType.Application.Json)

            body = request
        }

    override suspend fun register(request: RegisterRequest): RegisterResponse =
        client.post {
            url(HttpRoutes.REGISTER)
            contentType(ContentType.Application.Json)

            body = request
        }

    override suspend fun getAllLomba(): AllLombaDetailResponse =
        client.get{
            url(HttpRoutes.GET_ALL_LOMBA)
            contentType(ContentType.Application.Json)

            header("Authorization", "Bearer ${datastoreSource.getToken().first()}")
        }

    override suspend fun getLombaById(id: String): LombaDetailByIdResponse =
        client.get {
            url("${HttpRoutes.GET_LOMBA_BY_ID}/$id")
            contentType(ContentType.Application.Json)

            header("Authorization", "Bearer ${datastoreSource.getToken().first()}")
        }
}