package com.taokyone.aa_finalproject_android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.taokyone.aa_finalproject_android.databinding.FragmentNasaBinding
import com.taokyone.aa_finalproject_android.model.Nasa
import com.taokyone.aa_finalproject_android.model.apiData.NasaAPI
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class NasaFragment : Fragment() {

    // ViewBinding
    private lateinit var nasaBinding: FragmentNasaBinding

    // BaseURLs
    private val baseURLNasa = "https://api.nasa.gov/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Setup viewBinding
        nasaBinding = FragmentNasaBinding.inflate(layoutInflater, container, false)
        val view = nasaBinding.root

        // Id
        val nasaClickLayout = nasaBinding.layoutNasa
        val layoutClicker = nasaBinding.layoutNasa


        // Fetch API Method
        fun getNasa() {

            val retroFit = Retrofit.Builder().baseUrl(baseURLNasa)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val nasaImgAPI = retroFit.create<NasaAPI>().getNasaImage()

            nasaImgAPI.enqueue(object : Callback<Nasa> {
                override fun onResponse(call: Call<Nasa>, response: Response<Nasa>) {

                    val nasaImg = response.body()

                    if (!response.isSuccessful) {
                        println("Error...the daily image from NASA couldn't be loaded")

                    }

                    if (nasaImg != null) {

                        // NASA. Daily Info
                        nasaBinding.tvTitleNasa.text = "Title:  ${nasaImg.title}"
                        nasaBinding.tvDate.text = "Date: ${nasaImg.date}"
                        nasaBinding.tvCopyright.text = "Copyright: ${nasaImg.copyright}"
                        nasaBinding.tvExplanation.text = "Explanation: ${nasaImg.explanation}"

                        // NASA. Daily Image
                        Glide.with(nasaBinding.root)
                            .load(nasaImg.url)
                            .into(nasaBinding.ivNasaIMG)
                        nasaClickLayout.isVisible = true
                        YoYo.with(Techniques.FadeInUp).duration(700).repeat(0).playOn(nasaBinding.layoutNasa)
                    }
                }

                override fun onFailure(call: Call<Nasa>, t: Throwable) {
                    Toast.makeText(
                        activity,
                        t.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
        }

        // Click listener
        layoutClicker.setOnClickListener() {
            getNasa()
        }
        return view
    }
}

/*
fun getNasaImg () {

            val retroFit = Retrofit.Builder().
            baseUrl(baseURLNasa).
            addConverterFactory(GsonConverterFactory.create())
                .build()

            val nasaImgAPI = retroFit.create<NasaImageAPI>().getNasaImage()

            nasaImgAPI.enqueue(object : Callback<NasaImage> {
                override fun onResponse(call: Call<NasaImage>, response: Response<NasaImage>) {

                    val nasaImg = response.body()

                    if (!response.isSuccessful) {
                        println("Error...the daily image from NASA couldn't be loaded")
                    }

                    if (nasaImg != null) {
                        Glide.with(viewBinding.root)
                            .load(nasaImg.hdurl)
                            .into(viewBinding.ivNasaIMG)
                        YoYo.with(Techniques.BounceInDown)
                            .duration(500)
                            .repeat(0)
                            .playOn(viewBinding.flNasaFrame)

                    }
                }


 */