package com.example.dbm.inventoryappkt.presentation.util

import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.dbm.inventoryappkt.domain.service.IUserService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import javax.inject.Inject

interface IFirebaseAuthenticator {
    fun registerLifecycleOwner(activity: ComponentActivity, owner: LifecycleOwner)
}

class FirebaseAuthenticator @Inject constructor(
    private val userService: IUserService
): IFirebaseAuthenticator, LifecycleEventObserver {

    private lateinit var auth: FirebaseAuth
    private var activity: ComponentActivity? = null

    override fun registerLifecycleOwner(activity: ComponentActivity, owner: LifecycleOwner) {
        this.activity = activity
        owner.lifecycle.addObserver(this)
        auth = Firebase.auth
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_START -> {
                activity?.let {
                    if(auth.currentUser == null){

                        auth.signInAnonymously()
                            .addOnCompleteListener(it) { task ->
                                if (task.isSuccessful) {
                                    activity?.lifecycleScope?.launch {
                                        userService.setUserId(auth.currentUser)
                                    }
                                } else {
                                    activity?.lifecycleScope?.launch {
                                        userService.setUserId(null)
                                    }
                                }
                            }.addOnCanceledListener {
                                activity?.lifecycleScope?.launch {
                                    userService.setUserId(null)
                                }
                            }
                    } else {
                        activity?.lifecycleScope?.launch {
                            userService.setUserId(auth.currentUser)
                        }
                    }
                }
            }
            else -> Unit
        }
    }



}