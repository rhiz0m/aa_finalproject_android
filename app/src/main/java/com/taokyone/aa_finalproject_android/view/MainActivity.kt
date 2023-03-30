package com.taokyone.aa_finalproject_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.taokyone.aa_finalproject_android.R
import com.taokyone.aa_finalproject_android.databinding.ActivityMainBinding
import com.taokyone.aa_finalproject_android.model.CharacterInfo
import com.taokyone.aa_finalproject_android.model.apiData.RetroFitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val baseURL = "https://comicvine.gamespot.com/"
    lateinit var viewBinding: ActivityMainBinding
    var characterInfoList = ArrayList<CharacterInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)
        showPosts()
    }


    fun showPosts() {
        val retroFit = Retrofit.Builder().
        baseUrl(baseURL).
        addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI : RetroFitAPI = retroFit.create(RetroFitAPI::class.java)

        val call : Call<List<CharacterInfo>> = retrofitAPI.getCharacterInfo()

        // Callback function
        call.enqueue(object : Callback<List<CharacterInfo>> {
            override fun onResponse(call: Call<List<CharacterInfo>>, response: Response<List<CharacterInfo>>) {

                if(!response.isSuccessful) {
                    //viewBinding.imgCharacterId
                    viewBinding.tvNameId.text = "Error - name couldn't be found"
                    viewBinding.tvAliasesId.text = "Error - aliases couldn't be found"
                    viewBinding.tvDeckId.text = "Error - breif cescription couln't be found"
                }

                characterInfoList = response.body() as ArrayList<CharacterInfo>
                viewBinding.tvNameId.text = characterInfoList[0].name
                viewBinding.tvNameId.text = characterInfoList[0].aliases
                viewBinding.tvNameId.text = characterInfoList[0].deck

            }

            override fun onFailure(call: Call<List<CharacterInfo>>, t: Throwable) {
                Toast.makeText(applicationContext,
                    t.localizedMessage,
                    Toast.LENGTH_LONG).show()
            }

        })
    }
}