package com.example.dbm.inventoryappkt.data.local.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.dbm.inventoryappkt.global.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

interface IUserDataStore {
    suspend fun getUserId(): Flow<String>
    suspend fun setUserId(userId: String)
}

class UserDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
): IUserDataStore {

    override suspend fun getUserId(): Flow<String> {
        return dataStore.data.catch { exception ->
            // dataStore.data throws an IOException if it can't read the data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { prefs ->
            prefs[Constants.DATASTORE_KEY_USER_ID] ?: ""
        }
    }

    override suspend fun setUserId(userId: String) {
        dataStore.edit { prefs ->
            prefs[Constants.DATASTORE_KEY_USER_ID] = userId
        }
    }

} 