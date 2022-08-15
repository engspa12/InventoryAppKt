package com.example.dbm.inventoryappkt.domain.service

import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

interface IUserService {
    fun setUser(user: FirebaseUser?)
    fun getUser(): FirebaseUser?
}

class UserService @Inject constructor(
    //private val setUserUseCase: ISetUserUseCase,
    //private val getUserUseCase: IGetUserUseCase,
): IUserService {

    private var user: FirebaseUser? = null

    override fun setUser(user: FirebaseUser?) {
        this.user = user
    }

    override fun getUser(): FirebaseUser? {
        return this.user
    }

}