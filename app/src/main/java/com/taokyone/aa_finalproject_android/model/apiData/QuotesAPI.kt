package com.taokyone.aa_finalproject_android.model.apiData

import retrofit2.Call
import retrofit2.http.GET

interface QuotesAPI {

    // Calling the Call function of RetroFit library. Then, create a list of the type in the model class.
    @GET("/api/random")
    fun getWisdomQuote () : Call<List<Quotes>>

    //endpoint structure: array that contains objects
}