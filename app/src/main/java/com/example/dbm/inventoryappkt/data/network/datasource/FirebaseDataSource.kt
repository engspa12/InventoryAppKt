package com.example.dbm.inventoryappkt.data.network.datasource

import android.net.Uri
import android.util.Log
import com.example.dbm.inventoryappkt.global.Constants
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import javax.inject.Inject

interface IFirebaseDataSource {
    suspend fun uploadImageToStorage(selectedImageUri: Uri): FirebaseStorageResult
    suspend fun deleteProductInStorage(selectedImageUri: Uri): FirebaseStorageResult
}

@OptIn(ExperimentalCoroutinesApi::class)
class FirebaseStorageDataSource @Inject constructor() : IFirebaseDataSource {

    override suspend fun uploadImageToStorage(selectedImageUri: Uri): FirebaseStorageResult {
        val photoStorageReference = createReference(selectedImageUri.lastPathSegment ?: UUID.randomUUID().toString())

        return photoStorageReference.awaitImageUpload(selectedImageUri)
    }

    override suspend fun deleteProductInStorage(selectedImageUri: Uri): FirebaseStorageResult {
        val photoStorageReference = createReference(selectedImageUri.lastPathSegment ?: UUID.randomUUID().toString())

        return photoStorageReference.awaitImageDeletion()
    }


    private fun createReference(photoPathString: String): StorageReference {
        val firebaseStorage = Firebase.storage
        val storageReference = firebaseStorage.reference.child(Constants.STORAGE_FOLDER)

        return storageReference.child(photoPathString)
    }

    @ExperimentalCoroutinesApi
    suspend fun StorageReference.awaitImageUpload(
        selectedImageUri: Uri,
        onCancellation: ((cause: Throwable) -> Unit)? = null
    ) = suspendCancellableCoroutine<FirebaseStorageResult> { continuation ->

        val uploadTask = putFile(selectedImageUri)

        uploadTask
            .addOnFailureListener {
                continuation.resume(
                    FirebaseStorageResult.Error(it,it.message ?: "An error occurred while uploading the image to Firebase Storage"),
                    onCancellation
                )
            }
            .continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        //throw it
                        continuation.resume(
                            FirebaseStorageResult.Error(it,it.message ?: "An error occurred while uploading the image to Firebase Storage"),
                            onCancellation
                        )
                    }
                }
                downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val imageDownloadUri = task.result
                    continuation.resume(
                        FirebaseStorageResult.Success(downloadUrl = imageDownloadUri.toString()),
                        onCancellation
                    )
                } else {
                    if(!continuation.isCompleted){
                        continuation.resume(
                            FirebaseStorageResult.Error( task.exception ?: Exception("Download Url Exception") ,task.exception?.message ?: "An error occurred while retrieving downloadUrl from Firebase Storage"),
                            onCancellation
                        )
                    }
                }
            }

        continuation.invokeOnCancellation {
            Log.e("FirebaseDataSource", "The upload has been cancelled")
        }
    }


    @ExperimentalCoroutinesApi
    suspend fun StorageReference.awaitImageDeletion(
        onCancellation: ((cause: Throwable) -> Unit)? = null
    ) = suspendCancellableCoroutine<FirebaseStorageResult> { continuation ->

        val deleteTask = delete()

        deleteTask
            .addOnSuccessListener {
                continuation.resume(
                    FirebaseStorageResult.Success(""),
                    onCancellation
                )
            }
            .addOnFailureListener {
                continuation.resume(
                    FirebaseStorageResult.Error(it,it.message ?: "An error occurred while uploading the image to Firebase Storage"),
                    onCancellation
                )
            }

        continuation.invokeOnCancellation {
            Log.e("FirebaseDataSource", "The deletion has been cancelled")
        }
    }
}

sealed class FirebaseStorageResult {
    class Success(val downloadUrl: String): FirebaseStorageResult()
    class Error(val exception: Exception, val error: String): FirebaseStorageResult()
}