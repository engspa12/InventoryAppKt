package com.example.dbm.inventoryappkt.domain.repository

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun getUserId(): Flow<String>
    suspend fun setUserId(userId: String)
}