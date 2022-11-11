package com.example.happypetsapp.Services.Api.information

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.squareup.moshi.Json

class AnimalsResponse (
    val result :List<Animal>
    ){}

@Parcelize class Animal (
    val name: String,
    @Json(name = "taxonomy")
    val tax: Taxonomy? = null,
    @Json(name = "locations")
    val loc: List<String>,
    @Json(name = "characteristics")
    val details: Characteristics? = null,


) : Parcelable {var ref: String = "https://unsplash.com/photos/tIfrzHxhPYQ"}
@Parcelize class Taxonomy(
    @Json(name ="kingdom")
    val kin: String = "No Data",
    @Json(name = "phylum")
    val phy: String = "No Data",
    @Json(name = "class")
    val cla: String,
    @Json(name = "order")
    val ord: String = "No Data",
    @Json(name = "family")
    val fam:String = "No Data",
    @Json(name = "genus")
    val gen:String = "No Data",
    @Json(name = "scientific_name")
    var sname: String = "No data"
) : Parcelable
@Parcelize class Characteristics(
    @Json(name = "locations")
    val counties : List<String> = listOf(),
    @Json(name = "main_prey")
    val prey:String = "No data",
    @Json(name = "most_distinctive_feature")
    val specialf :String = "No data",
    @Json(name = "habitat" )
    val habitat:String = "No data",
    @Json(name = "predators" )
    val predators:String = "No data",
    @Json(name = "diet" )
    val diet:String = "No data",
    @Json(name = "lifespan")
    val lspan:String = "No data",
    @Json(name = "group_behavior")
    val group:String = "No data",
    @Json(name = "age_of_sexual_maturity")
    var age_of_sexual_maturity:String = "No data",
    var type:String = "No data",

    var color:String = "No data",
    var skin_type:String = "No data",
    var top_speed:String = "No data",

    var weight:String = "No data",
    var length:String = "No data",

    var age_of_weaning:String = "No data",

) : Parcelable