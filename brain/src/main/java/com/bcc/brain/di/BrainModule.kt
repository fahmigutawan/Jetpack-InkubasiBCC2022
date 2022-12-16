package com.bcc.brain.di

import android.content.Context
import com.bcc.brain.data.datastore.DatastoreSource
import com.bcc.brain.data.ktor.ApiServices
import com.bcc.brain.data.ktor.ApiServicesImpl
import com.bcc.brain.data.local.LocalDataSource
import com.bcc.brain.data.remote.RemoteDataSource
import com.bcc.brain.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.ANDROID
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BrainModule {
    @Provides
    @Singleton
    fun provideHttpClient() = HttpClient(Android) {
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.HEADERS
        }
        install(HttpTimeout) { // Timeout
            requestTimeoutMillis = 10000L
            connectTimeoutMillis = 10000L
            socketTimeoutMillis = 10000L
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = false
                }
            )
        }
    }

    @Provides
    @Singleton
    fun provideLocalDataSource() = LocalDataSource()

    @Provides
    @Singleton
    fun provideDatastoreSource(@ApplicationContext context: Context) =
        DatastoreSource(context = context)

    @Provides
    @Singleton
    fun provideApiServices(client: HttpClient, datastoreSource: DatastoreSource): ApiServices =
        ApiServicesImpl(client, datastoreSource)

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiServices: ApiServices) = RemoteDataSource(apiServices)

    @Provides
    @Singleton
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        datastoreSource: DatastoreSource
    ) = Repository(
        datastoreSource = datastoreSource,
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource
    )
}