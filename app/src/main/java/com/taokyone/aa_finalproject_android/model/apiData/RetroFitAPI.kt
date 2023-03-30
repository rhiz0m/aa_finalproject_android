package com.taokyone.aa_finalproject_android.model.apiData

import com.taokyone.aa_finalproject_android.model.CharacterInfo
import retrofit2.Call
import retrofit2.http.GET

interface RetroFitAPI {

    // Calling the Call function of RetroFit library. Then, create a list of the type in the model class.

    @GET("/api/character/4005-2149/?api_key=38763a6324d1ad934bc717ab9d4628f4d7c076cb")
    fun getCharacterInfo () : Call<List<CharacterInfo>>

}