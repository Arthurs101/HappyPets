package com.example.happypetsapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PetModel(
    val name: String = "-",
    val breed: String = "-",
    val alergy: String = "-",
    val medical: String = "-",
    val age : String = "00",
    var imgref : String = "-"
) : Parcelable {
}