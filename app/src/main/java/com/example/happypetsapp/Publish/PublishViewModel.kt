package com.example.happypetsapp.Publish

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.happypetsapp.Services.Firebase.FirebaseSingleton
import com.example.happypetsapp.models.PublicationModel

class PublishViewModel : ViewModel() {
    var publication: Boolean = false //enviar al feed de publicacion
    val temporalPublicationModel = PublicationModel (
        FirebaseSingleton.getUser()?.photoUrl?.toString().toString(),
        "",
        "",
        FirebaseSingleton.getUser()?.displayName?.toString().toString())
    fun setRef(uri: String){
        temporalPublicationModel.publicationImageRef = uri
    }
    fun setContent(content: String){
        temporalPublicationModel.description = content
    }
    fun pulish(){
        when(publication){
            true -> {FirebaseSingleton.uploadPublication(temporalPublicationModel)}

            false-> {FirebaseSingleton.uploadAlert(temporalPublicationModel)}
        }


    }
}