package com.bcc.brain.data.repository

import com.bcc.brain.data.datastore.DatastoreSource
import com.bcc.brain.data.local.LocalDataSource
import com.bcc.brain.data.remote.RemoteDataSource
import com.bcc.brain.model.data.response.LoginResponse
import com.bcc.brain.model.data.response.RegisterResponse
import com.bcc.brain.model.domain.LombaDetailDomain
import com.bcc.brain.utils.ApiResponse
import com.bcc.brain.utils.MapperResponseToDomain
import com.bcc.brain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val datastoreSource: DatastoreSource,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    // save firsttime state
    suspend fun saveFirstTimeState(firstTime: Boolean) =
        datastoreSource.saveFirstTimeState(firstTime)

    // get firsttime state
    fun getFirstTimeState() = datastoreSource.getFirstTimeState()

    // save auth token
    suspend fun saveToken(token: String) = datastoreSource.saveToken(token)

    // get auth token
    fun getToken() = datastoreSource.getToken()

    // login
    fun login(email: String, password: String): Flow<Resource<LoginResponse>> = flow {
        emit(Resource.Loading())

        when (val result = remoteDataSource.login(email, password).first()) {
            is ApiResponse.Error -> {
                emit(Resource.Error(result.errorMessage))
            }

            is ApiResponse.Success -> {
                emit(Resource.Success(result.data))
            }
        }
    }

    // register
    fun register(
        email: String,
        password: String,
        confirm_password: String,
        nama: String
    ): Flow<Resource<RegisterResponse>> = flow {
        emit(Resource.Loading())

        when (val result = remoteDataSource.register(
            email,
            password,
            confirm_password,
            nama
        ).first()
        ) {
            is ApiResponse.Error -> {
                emit(Resource.Error(result.errorMessage))
            }

            is ApiResponse.Success -> {
                emit(Resource.Success(result.data))
            }
        }
    }

    // get All lomba
    fun getAllLomba(): Flow<Resource<List<LombaDetailDomain>>> = flow {
        emit(Resource.Loading())

        when (val result = remoteDataSource.getAllLomba().first()) {
            is ApiResponse.Error -> {
                emit(Resource.Error(result.errorMessage))
            }

            is ApiResponse.Success -> {
                emit(Resource.Success(
                    result.data.data.map {
                        MapperResponseToDomain.mapLombaDetail(it)
                    }
                ))
            }
        }
    }

    // get Lomba by ID
    fun getLombaById(id: String): Flow<Resource<LombaDetailDomain>> = flow {
        emit(Resource.Loading())

        when (val result = remoteDataSource.getLombaById(id).first()) {
            is ApiResponse.Error -> {
                emit(Resource.Error(result.errorMessage))
            }

            is ApiResponse.Success -> {
                emit(
                    Resource.Success(
                        MapperResponseToDomain.mapLombaDetail(result.data.data)
                    )
                )
            }
        }
    }
}