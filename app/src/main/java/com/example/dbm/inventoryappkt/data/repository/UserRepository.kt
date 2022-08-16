package com.example.dbm.inventoryappkt.data.repository

import com.example.dbm.inventoryappkt.data.local.datasource.IUserDataStore
import com.example.dbm.inventoryappkt.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDataStore: IUserDataStore
): IUserRepository {

    override suspend fun getUserId(): Flow<String> {
       return userDataStore.getUserId()
    }

    override suspend fun setUserId(userId: String) {
        userDataStore.setUserId(userId)
    }
}