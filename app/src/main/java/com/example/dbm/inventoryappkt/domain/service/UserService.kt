package com.example.dbm.inventoryappkt.domain.service

import com.example.dbm.inventoryappkt.domain.usecase.IGetUserIdUseCase
import com.example.dbm.inventoryappkt.domain.usecase.ISetUserIdUseCase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface IUserService {
    suspend fun setUserId(user: FirebaseUser?)
    suspend fun getUserId(): Flow<String?>
}

class UserService @Inject constructor(
    private val setUserUseCase: ISetUserIdUseCase,
    private val getUserUseCase: IGetUserIdUseCase,
): IUserService {

    override suspend fun setUserId(user: FirebaseUser?) {
        val userId = user?.uid.orEmpty()
        setUserUseCase(userId)
    }

    override suspend fun getUserId(): Flow<String?> {
        return getUserUseCase().map { userId ->
            userId.ifEmpty {
                null
            }
        }
    }

}