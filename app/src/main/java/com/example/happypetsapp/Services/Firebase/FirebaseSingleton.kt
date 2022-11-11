package com.example.happypetsapp.Services.Firebase

import com.example.happypetsapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object FirebaseSingleton {


    val Firebaseauth = Firebase.auth
    private var User = Firebaseauth.currentUser
    fun getUser(): FirebaseUser? {
        return User
    }

    fun refreshUser() {
        User = Firebaseauth.currentUser
    }





}