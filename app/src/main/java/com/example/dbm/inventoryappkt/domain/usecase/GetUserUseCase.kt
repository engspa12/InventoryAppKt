package com.example.dbm.inventoryappkt.domain.usecase

import com.example.dbm.inventoryappkt.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IGetUserIdUseCase {
    suspend operator fun invoke(): Flow<String>
}

class GetUserIdUseCase @Inject constructor(
    private val userRepository: IUserRepository
): IGetUserIdUseCase {

    override suspend fun invoke(): Flow<String> {
        return userRepository.getUserId()
    }

}