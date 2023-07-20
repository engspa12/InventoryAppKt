package com.example.dbm.inventoryappkt.domain.usecase

import com.example.dbm.inventoryappkt.domain.repository.IUserRepository
import javax.inject.Inject

interface ISetUserIdUseCase {
    suspend operator fun invoke(userId: String)
}

class SetUserIdUseCase @Inject constructor(
    private val userRepository: IUserRepository
): ISetUserIdUseCase {

    override suspend fun invoke(userId: String) {
        userRepository.setUserId(userId)
    }

}