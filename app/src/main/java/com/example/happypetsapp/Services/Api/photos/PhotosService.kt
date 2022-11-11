package com.example.happypetsapp.Services.Api.photos

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotosService {
    @GET("search/photos")
    fun getImage(@Query("per_page") items: Int ,
                 @Query("query") search: String,
                 @Query("client_id") id: String
                 ): Call<FotosResponse>
}