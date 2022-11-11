package com.example.happypetsapp.Services.Api.photos

import com.example.happypetsapp.Services.Api.information.AnimalsAPI
import com.example.happypetsapp.Services.Api.information.AnimalsService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PhotosApi {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val retrofit = Retrofit.Builder()  //objeto de tipo retrofit que se conecta a la base de datos
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(MoshiConverterFactory.create(AnimalsAPI.moshi))
        .build()
    val service: PhotosService by lazy { retrofit.create (PhotosService::class.java)  } //crear el servicio (el cual maneja las operaciones de requests a la base datos)
}