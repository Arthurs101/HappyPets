package com.example.happypetsapp.Services.Firebase

import android.net.Uri
import android.util.Log
import com.example.happypetsapp.models.PublicationModel
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
     fun uploadImage(imageUri: Uri) : String{
         val storageReference = FirebaseStorage.getInstance().getReference("images").child(User!!.displayName!!).child(Calendar.getInstance().time.toString()).putFile(imageUri)
         var message = ""
         storageReference.addOnCompleteListener{
             if(it.isSuccessful){
                 message = "Succes"
             }else{
                 Log.d("failure Image" , "I dunno Why")
             }
         }
         return message

     }
}