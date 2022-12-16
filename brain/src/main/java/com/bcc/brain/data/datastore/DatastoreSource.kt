package com.bcc.brain.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.myDataStore: DataStore<Preferences> by preferencesDataStore(name = "silomba_settings")

class DatastoreSource @Inject constructor(
    context: Context
) {
    private val dataStore = context.myDataStore

    /**First Time state*/
    suspend fun saveFirstTimeState(firstTime: Boolean) {
        dataStore.edit {
            it[booleanPreferencesKey("is_first_time")] = firstTime
        }
    }

    fun getFirstTimeState() =
        dataStore.data.map { it[booleanPreferencesKey("is_first_time")] ?: true }

    /**Token*/
    suspend fun saveToken(token: String) {
        dataStore.edit { it[stringPreferencesKey("bearer_token")] = token }
    }

    fun getToken() = dataStore.data.map { it[stringPreferencesKey("bearer_token")] ?: "" }
}