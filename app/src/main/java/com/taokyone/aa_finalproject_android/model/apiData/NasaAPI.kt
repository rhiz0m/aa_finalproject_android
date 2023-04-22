package com.taokyone.aa_finalproject_android.model.apiData

import com.taokyone.aa_finalproject_android.model.Nasa
import retrofit2.Call
import retrofit2.http.GET

interface NasaAPI {

    @GET("/planetary/apod?api_key=DEMO_KEY")
    fun getNasaImage () : Call<Nasa>

}