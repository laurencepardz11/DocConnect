package com.nethealth.drconnect.app.state

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.nethealth.drconnect.vo.Jwt
import kotlinx.coroutines.flow.map

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "UserData")

class DataStorage (context: Context) {
    private val userDataStore = context.userDataStore

    companion object {
        private val KEY_USER_DATA = stringPreferencesKey("user_data")
        private val KEY_HAS_SESSION = booleanPreferencesKey("has_session")
    }

    // Save user data object in DataStore
    suspend fun saveUserData(userData: Jwt) {
        val json = Gson().toJson(userData)
        userDataStore.edit { preferences ->
            preferences[KEY_USER_DATA] = json
            preferences[KEY_HAS_SESSION] = true
        }
    }

    // Retrieve user data object from DataStore
    val getUserData = userDataStore.data
        .map { preferences ->
            val json = preferences[KEY_USER_DATA] ?: ""
            Gson().fromJson(json, Jwt::class.java)
        }

    // Clear user data object from DataStore
    suspend fun clearUserData() {
        userDataStore.edit { preferences ->
            preferences.clear()
        }
    }

    // Check if session exists in DataStore
    val hasSession = userDataStore.data.map { preferences ->
        preferences[KEY_HAS_SESSION] ?: false
    }
}