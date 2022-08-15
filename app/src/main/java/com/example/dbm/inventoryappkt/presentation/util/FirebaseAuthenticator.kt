package com.example.dbm.inventoryappkt.presentation.util

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.dbm.inventoryappkt.domain.service.IUserService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
                    auth.signInAnonymously().addOnCompleteListener(it) { task ->
                        if (task.isSuccessful) {
                            userService.setUser(auth.currentUser)
                            Log.d("FirebaseAuthenticator","User authenticated")
                        } else {
                            userService.setUser(null)
                            Log.d("FirebaseAuthenticator","User not authenticated. Reason: ${task.exception?.cause}")
                        }
                    }.addOnCanceledListener {
                        userService.setUser(null)
                        Log.e("FirebaseAuthenticator","The sign in was canceled")
                    }
                }
            }
            Lifecycle.Event.ON_STOP -> {
                auth.signOut()
            }
            Lifecycle.Event.ON_DESTROY -> {
                activity = null
            }
            else -> Unit
        }
    }



}