package com.example.happypetsapp.Services.Firebase

import android.net.Uri
import android.util.Log
import com.example.happypetsapp.Publish.PublishViewModel
import com.example.happypetsapp.models.PublicationModel
import com.example.happypetsapp.models.privatePub
import com.example.happypetsapp.ui.PetDisplayDatabase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.util.*


object FirebaseSingleton {

    val RealTimeData = Firebase.database.reference
    val Firebaseauth = Firebase.auth
    private var User = Firebaseauth.currentUser

    fun getUser(): FirebaseUser? {
        return User
    }

    fun refreshUser() {
        User = Firebaseauth.currentUser
    }

    fun SendPublication(publication: PublicationModel): String{
        User?.let {
            RealTimeData.child("publications").child(User!!.displayName!!).setValue(publication)
            return "Succes"
        }
        return "Failure, User is null"
    }
     fun uploadImage(imageUri: Uri, vim: PublishViewModel) {
         //subir imagen a firebase Stgorage
         val ref = FirebaseStorage.getInstance().getReference("images").child(User!!.displayName!!).child(User!!.uid).child(Calendar.getInstance().time.toString())
         val uploadTask = ref.putFile(imageUri)
        //recuoerar el link de la imagen
         val urlTask = uploadTask.continueWithTask { task ->
             if (!task.isSuccessful) {
                 task.exception?.let {
                     throw it
                 }
             }
             ref.downloadUrl
         }.addOnCompleteListener { task ->
             if (task.isSuccessful) {
                 val downloadUri = task.result
                 vim.temporalPublicationModel!!.publicationImageRef = downloadUri.toString()
             } else {
                 // Handle failures
                 // ...
             }
         }

     }
    fun uploadImage(imageUri: Uri, vim: PetDisplayDatabase) {
        //subir imagen a firebase Stgorage

        val ref = FirebaseStorage.getInstance().getReference("images").child(User!!.displayName!!).child(User!!.uid).child(Calendar.getInstance().time.toString())
        val uploadTask = ref.putFile(imageUri)
        //recuoerar el link de la imagen
        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                vim.temp.imgref = downloadUri.toString()
            } else {
                // Handle failures
                // ...
            }
        }

    }
    fun uploadImage(imageUri: Uri) : String {
        var urlReference = " "
        //subir imagen a firebase Stgorage

        val ref = FirebaseStorage.getInstance().getReference("images").child(User!!.displayName!!).child(User!!.uid).child(Calendar.getInstance().time.toString())
        val uploadTask = ref.putFile(imageUri)
        //recuoerar el link de la imagen
        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                urlReference = downloadUri.toString()
            } else {
                // Handle failures
                // ...
            }
        }
        return  urlReference

    }
    fun uploadPublication(pub: PublicationModel){

        //global
        RealTimeData.child("publications").child("global").push().setValue(pub)
        // privateForUsers
        RealTimeData.child("publications").child(pub.username).push().setValue(privatePub(pub.description,pub.publicationImageRef))

    }

    fun uploadAlert(pub: PublicationModel) {
        RealTimeData.child("alerts").child("global").push().setValue(pub)

    }
    fun getauthPhoto(url: String): String{
        var ref = "."
        val storageReference = Firebase.storage.getReferenceFromUrl(url)
        storageReference.downloadUrl
            .addOnSuccessListener { uri ->
                val downloadUrl = uri.toString()
                ref = downloadUrl
            }
        return ref
    }
    fun getPhotoUrl(): String? {
        val user = Firebase.auth.currentUser
        return user?.photoUrl?.toString()
    }
}