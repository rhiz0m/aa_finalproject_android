package com.taokyone.aa_finalproject_android.view



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.taokyone.aa_finalproject_android.databinding.FragmentAboutBinding
import com.taokyone.aa_finalproject_android.model.NasaImage
import com.taokyone.aa_finalproject_android.model.apiData.NasaImageAPI
import com.taokyone.aa_finalproject_android.model.Quotes
import com.taokyone.aa_finalproject_android.model.apiData.QuotesAPI
import com.taokyone.aa_finalproject_android.viewModel.NasaViewModel
import com.taokyone.aa_finalproject_android.viewModel.QuotesViewModel
import io.github.florent37.shapeofview.shapes.CutCornerView
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class AboutFragment : Fragment() {

    private val quotesViewModel by viewModels<QuotesViewModel>()
    private val nasaViewModel by viewModels<NasaViewModel>()

    // BaseURLs
    private val baseURLQuotes = "https://zenquotes.io/"
    private val baseURLNasa = "https://api.nasa.gov/"

    // ViewBinding
    private lateinit var viewBinding: FragmentAboutBinding
    var quotesList = ArrayList<Quotes>()

    // Clickable
    private lateinit var nasaClickableImg: ImageView
    private lateinit var quotesClickable: CutCornerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Setup viewBinding

        viewBinding = FragmentAboutBinding.inflate(layoutInflater,  container, false)
        val view = viewBinding.root

        // Clickable images

        nasaClickableImg = viewBinding.ivNasaIMG
        quotesClickable = viewBinding.ccwQuotes

        //Lifecycle
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                quotesViewModel.uiState.collect() {
                    //nasaClickableImg = nasaViewModel.uiState.value.hdurl


                }
            }
        }


        // Fetch API Methods
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

                override fun onFailure(call: Call<NasaImage>, t: Throwable) {
                    Toast.makeText(activity,
                        t.localizedMessage,
                        Toast.LENGTH_LONG).show()
                }

            })
        }

        fun getWisdomQuotes() {
            val retroFit = Retrofit.Builder().baseUrl(baseURLQuotes)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val quotesAPI: QuotesAPI = retroFit.create(QuotesAPI::class.java)

            val call: Call<List<Quotes>> = quotesAPI.getWisdomQuote()

            // Callback function
            call.enqueue(object : Callback<List<Quotes>> {
                override fun onResponse(
                    call: Call<List<Quotes>>,
                    response: Response<List<Quotes>>
                ) {

                    if (!response.isSuccessful) {
                        viewBinding.tvQuoteId.text = "Error - quote couldn't be found..."
                        viewBinding.tvAuthorId.text = "Error - author couldn't be found..."
                    }

                    quotesList = response.body() as ArrayList<Quotes>
                    viewBinding.tvQuoteId.text = quotesList[0].q
                    viewBinding.tvAuthorId.text = quotesList[0].a
                    YoYo.with(Techniques.BounceInUp)
                        .duration(700)
                        .repeat(0)
                        .playOn(viewBinding.flTextFrame)

                }

                override fun onFailure(call: Call<List<Quotes>>, t: Throwable) {
                    Toast.makeText(
                        activity,
                        t.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
        }

        // Buttons

        nasaClickableImg.setOnClickListener {
            getNasaImg()
        }

        quotesClickable.setOnClickListener {
            getWisdomQuotes()
        }
/*
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                quotesViewModel.uiState.collect() {
                    viewBinding.tvQuoteId.text = quotesViewModel.uiState.value.q
                    viewBinding.tvAuthorId.text = quotesViewModel.uiState.value.a

                }
            }
        }
*/


        return view
    }

}