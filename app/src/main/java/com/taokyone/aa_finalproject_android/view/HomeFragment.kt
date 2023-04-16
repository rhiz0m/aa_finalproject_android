package com.taokyone.aa_finalproject_android.view

import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.taokyone.aa_finalproject_android.databinding.FragmentHomeBinding
import com.taokyone.aa_finalproject_android.model.NasaImage
import com.taokyone.aa_finalproject_android.model.apiData.NasaImageAPI
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private val baseURLNasa = "https://api.nasa.gov/"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = homeBinding.root

        fun getNasaUrl () {

            val retroFit = Retrofit.Builder().
            baseUrl(baseURLNasa).
            addConverterFactory(GsonConverterFactory.create())
                .build()

            val nasaImgAPI = retroFit.create<NasaImageAPI>().getNasaImage()

            nasaImgAPI.enqueue(object : Callback<NasaImage> {
                override fun onResponse(call: retrofit2.Call<NasaImage>, response: Response<NasaImage>) {

                    val nasaImgUrl = response.body()

                    if (response.isSuccessful) {
                        homeBinding.tvHome.text = "testing Url + ${nasaImgUrl}"
                    }
                }

                override fun onFailure(call: retrofit2.Call<NasaImage>, t: Throwable) {
                    Toast.makeText(activity,
                        t.localizedMessage,
                        Toast.LENGTH_LONG).show()
                }

            })
        }

        getNasaUrl()


        return view
    }


}




