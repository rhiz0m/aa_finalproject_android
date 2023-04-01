package com.taokyone.aa_finalproject_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.taokyone.aa_finalproject_android.databinding.ActivityMainBinding
import com.taokyone.aa_finalproject_android.model.apiData.NasaImage
import com.taokyone.aa_finalproject_android.model.apiData.NasaImageAPI
import com.taokyone.aa_finalproject_android.model.apiData.WisdomQuotes
import com.taokyone.aa_finalproject_android.model.apiData.WisdomQuotesAPI
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // BaseURLs
    private val baseURLQuotes = "https://zenquotes.io/"
    private val baseURLNasa = "https://api.nasa.gov/"

    // ViewBinding
    lateinit var viewBinding: ActivityMainBinding

    var wisdomList = ArrayList<WisdomQuotes>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        // Running the API-calls
        showWisdomQuotes()
        showNasaImg()
    }

    private fun showNasaImg () {
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
                    }
            }

            override fun onFailure(call: Call<NasaImage>, t: Throwable) {
                Toast.makeText(applicationContext,
                    t.localizedMessage,
                    Toast.LENGTH_LONG).show()
            }

        })
    }
    private fun showWisdomQuotes() {
        val retroFit = Retrofit.Builder().
        baseUrl(baseURLQuotes).
        addConverterFactory(GsonConverterFactory.create())
            .build()

        val quotesAPI : WisdomQuotesAPI = retroFit.create(WisdomQuotesAPI::class.java)

        val call : Call<List<WisdomQuotes>> = quotesAPI.getWisdomQuote()

        // Callback function
        call.enqueue(object : Callback<List<WisdomQuotes>> {
            override fun onResponse(call: Call<List<WisdomQuotes>>, response: Response<List<WisdomQuotes>>) {

                if(!response.isSuccessful) {
                    viewBinding.tvQuoteId.text = "Error - quote couldn't be found..."
                    viewBinding.tvAuthorId.text = "Error - author couldn't be found..."
                }

                wisdomList = response.body() as ArrayList<WisdomQuotes>
                viewBinding.tvQuoteId.text = wisdomList[0].q
                viewBinding.tvAuthorId.text = wisdomList[0].a

            }

            override fun onFailure(call: Call<List<WisdomQuotes>>, t: Throwable) {
                Toast.makeText(applicationContext,
                    t.localizedMessage,
                    Toast.LENGTH_LONG).show()
            }

        })
    }
}