package com.example.happypetsapp.Services.Api.information

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AnimalsAPI {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val retrofit = Retrofit.Builder()  //objeto de tipo retrofit que se conecta a la base de datos
        .baseUrl("https://api.api-ninjas.com/v1/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    val service: AnimalsService by lazy { retrofit.create (AnimalsService::class.java)  } //crear el servicio (el cual maneja las operaciones de requests a la base datos)
}