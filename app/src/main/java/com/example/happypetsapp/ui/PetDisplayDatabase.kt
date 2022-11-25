package com.example.happypetsapp.ui

import androidx.lifecycle.ViewModel
import com.example.happypetsapp.Services.Firebase.FirebaseSingleton
import com.example.happypetsapp.models.PetModel

class PetDisplayDatabase : ViewModel() {
    var temp = PetModel()

    fun publish(pt: PetModel) : String{
        val ref = FirebaseSingleton.RealTimeData.child("mascots").child(FirebaseSingleton.Firebaseauth.currentUser?.displayName?.toString().toString())
        if(temp.imgref.isNotEmpty()){
            pt.imgref = temp.imgref
        }
        if (pt.name.isNotBlank()) {
            ref.push().setValue(pt)
            return "succes"
        }else{
            return "Ingrese por lo menos el nombre"
        }
    }
}