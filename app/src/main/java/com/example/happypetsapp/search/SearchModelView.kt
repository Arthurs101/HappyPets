package com.example.happypetsapp.search


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.happypetsapp.Services.Api.information.Animal
import com.example.happypetsapp.Services.Api.information.AnimalsAPI
import com.example.happypetsapp.Services.Api.information.AnimalsResponse
import com.example.happypetsapp.Services.Api.information.AnimalsService
import com.example.happypetsapp.Services.Api.photos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(private val Service: AnimalsService, private val ImageService: PhotosService) :ViewModel() {
    //lsita vacía de animales
     var breeds: List<Animal>  = listOf()
    private var lastSearch:String = ""


    fun searchAnimals(search:String)  {
        if(lastSearch.lowercase() != search.lowercase()){
            val apianswer = Service.getSearch(search)
            apianswer.enqueue(object: Callback<List<Animal>>{
                override fun onResponse(
                    call: Call<List<Animal>>,
                    response: Response<List<Animal>>
                ) {
                    if(response.isSuccessful){
                        val animals = response.body()
                        animals?.let {
                            breeds = animals
                            Log.d("Animals: ", "${breeds.toString()}")
                        }
                    }
                }

                override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                    breeds = listOf()
                    Log.d("Failed to get animals", "${t.toString()}")

                }

            }
            )
        }

    }
    fun searchImage(search: String): String{
        var photo = photo(link(""))
        val EngineAnswer = ImageService.getImage(1,search,"800brOGSKNk8-FzkmKCG81c5_mohzK0brf1Ra-t9Yoc")
        EngineAnswer.enqueue(object: Callback<FotosResponse> {
            override fun onResponse(call: Call<FotosResponse>, response: Response<FotosResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        it.fetched?.let {
                            if (!it.isEmpty()){
                                photo = it.get(0)
                                Log.d("Fotography: ", "${photo.Refs?.ref}")
                            }
                        }
                    }
                }else{
                    Log.d("Error On Api: " , "${response.errorBody()?.string()}}")
                }
            }

            override fun onFailure(call: Call<FotosResponse>, t: Throwable) {
                Log.d("Failed to get Foto", "${t.toString()}")
            }


        })
        var temp:String=""
        photo.Refs?.let { temp = it.ref }
        return temp
    }

fun SavingFotosFromAdapter(withimage: List<Animal>){
    breeds = withimage
}

}
class SearchViewModelFacotry(private val Service: AnimalsService, private val PhotoEngine: PhotosService) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(Service,PhotoEngine) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}