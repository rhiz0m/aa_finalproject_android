package com.taokyone.aa_finalproject_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.navigation.NavigationBarView
import com.taokyone.aa_finalproject_android.databinding.ActivityMainBinding
import com.taokyone.aa_finalproject_android.model.apiData.NasaImage
import com.taokyone.aa_finalproject_android.model.apiData.NasaImageAPI
import com.taokyone.aa_finalproject_android.model.apiData.Quotes
import com.taokyone.aa_finalproject_android.model.apiData.QuotesAPI
import io.github.florent37.shapeofview.shapes.CutCornerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    // BaseURLs
    private val baseURLQuotes = "https://zenquotes.io/"
    private val baseURLNasa = "https://api.nasa.gov/"
    private lateinit var nasaClickableImg: ImageView
    private lateinit var quotesClickable: CutCornerView

    // ViewBinding
    private lateinit var viewBinding: ActivityMainBinding

    var quotesList = ArrayList<Quotes>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        // OnItemListener for the Bottom Navigation
        viewBinding.bottomNavView.setOnItemSelectedListener(this)

    }

    private fun onHomeClicked () {
        supportFragmentManager.commit {
            replace(R.id.fcw_content, HomeFragment())
        }
    }
    private fun onNotesClicked () {
        supportFragmentManager.commit {
            replace(R.id.fcw_content, NotesFragment())
        }
    }
    private fun onExploreClicked () {
        supportFragmentManager.commit {
            replace(R.id.fcw_content, ExploreFragment())
        }
    }
    private fun onAboutClicked () {
        supportFragmentManager.commit {
            replace(R.id.fcw_content, AboutFragment())
        }
    }

    // Setting
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_home -> {
                onHomeClicked()
                true
            }
            R.id.nav_notes -> {
                onNotesClicked()
                true
            }
            R.id.nav_explore -> {
                onExploreClicked()
                true
            }
            R.id.nav_about -> {
                onAboutClicked()
                true
            }
            else -> {
                false
            }
        }
    }
}

/*
        // Buttons and Listeners
        nasaClickableImg = viewBinding.ivNasaIMG
        quotesClickable = viewBinding.ccwQuotes

        nasaClickableImg.setOnClickListener {
            getNasaImg()
            YoYo.with(Techniques.BounceInDown)
                .duration(700)
                .repeat(0)
                .playOn(viewBinding.flNasaFrame)


        }
        quotesClickable.setOnClickListener {
            getWisdomQuotes()
            YoYo.with(Techniques.BounceInUp)
                .duration(700)
                .repeat(0)
                .playOn(viewBinding.flTextFrame)

        }
    }

    // Fetch API Methods
    private fun getNasaImg () {
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
    private fun getWisdomQuotes() {
        val retroFit = Retrofit.Builder().
        baseUrl(baseURLQuotes).
        addConverterFactory(GsonConverterFactory.create())
            .build()

        val quotesAPI : QuotesAPI = retroFit.create(QuotesAPI::class.java)

        val call : Call<List<Quotes>> = quotesAPI.getWisdomQuote()

        // Callback function
        call.enqueue(object : Callback<List<Quotes>> {
            override fun onResponse(call: Call<List<Quotes>>, response: Response<List<Quotes>>) {

                if(!response.isSuccessful) {
                    viewBinding.tvQuoteId.text = "Error - quote couldn't be found..."
                    viewBinding.tvAuthorId.text = "Error - author couldn't be found..."
                }

                quotesList = response.body() as ArrayList<Quotes>
                viewBinding.tvQuoteId.text = quotesList[0].q
                viewBinding.tvAuthorId.text = quotesList[0].a

            }

            override fun onFailure(call: Call<List<Quotes>>, t: Throwable) {
                Toast.makeText(applicationContext,
                    t.localizedMessage,
                    Toast.LENGTH_LONG).show()
            }

        })
    }

*/