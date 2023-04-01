package com.taokyone.aa_finalproject_android.model.apiData

import retrofit2.Call
import retrofit2.http.GET

interface NasaImageAPI {

    @GET("/planetary/apod?api_key=DEMO_KEY")
    fun getNasaImage () : Call<NasaImage>

}