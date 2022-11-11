package com.example.happypetsapp.Services.Api.information

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AnimalsService {
    @Headers("X-Api-Key:ddBWn9gozdQypjs+AmiVQg==OmLn6NLw0y6oRAKr")
    @GET("animals")
    fun getSearch(@Query("name") name: String): Call<List<Animal>>
}