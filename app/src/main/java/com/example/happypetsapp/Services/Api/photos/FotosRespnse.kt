package com.example.happypetsapp.Services.Api.photos

import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
@Parcelize class FotosResponse (

    @Json(name = "results")
    val fetched: List<photo>

    ) : Parcelable
{
}
@Parcelize class photo(
    @Json(name = "links")
    val Refs: link? = null
) : Parcelable
@Parcelize class link(
    @Json(name = "html")
    val ref: String = ""
) : Parcelable