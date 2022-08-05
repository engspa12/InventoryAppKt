package com.example.dbm.inventoryappkt.data.network.datasource

import android.net.Uri
import com.example.dbm.inventoryappkt.global.Constants
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

interface IFirebaseDataSource {
    suspend fun uploadImageToStorage(photoPathString: String, selectedImageUri: Uri)
    //suspend fun getDownloadUrl(photoPathString: String, selectedImageUri: Uri)
}

class FirebaseStorageDataSource @Inject constructor(
) : IFirebaseDataSource {

    override suspend fun uploadImageToStorage(photoPathString: String, selectedImageUri: Uri) {

        val photoReference = createReference(photoPathString)
        val uploadTask = photoReference.putFile(selectedImageUri)
        var uploadWasSuccessful = false

        uploadTask.addOnSuccessListener {
            uploadWasSuccessful = true
        }.addOnFailureListener {
            uploadWasSuccessful = false
        }.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            photoReference.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val imageDownloadUri = task.result
            } else {

            }
        }
    }

    /*override suspend fun getDownloadUrl(photoPathString: String, selectedImageUri: Uri) {

        val photoReference = createReference(photoPathString)
        val uploadTask = photoReference.putFile(selectedImageUri)

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            photoReference.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val imageDownloadUri = task.result
            } else {

            }
        }
    }*/

    private fun createReference(photoPathString: String): StorageReference {
        val firebaseStorage = Firebase.storage
        val storageReference = firebaseStorage.reference.child(Constants.STORAGE_FOLDER)

        return storageReference.child(photoPathString)
    }

}