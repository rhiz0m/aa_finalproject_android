package com.taokyone.aa_finalproject_android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.taokyone.aa_finalproject_android.databinding.FragmentHomeBinding
import com.taokyone.aa_finalproject_android.model.Quotes
import com.taokyone.aa_finalproject_android.model.apiData.QuotesAPI
import com.taokyone.aa_finalproject_android.viewModel.DateTimeViewModel
import io.github.florent37.shapeofview.shapes.CutCornerView
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private val dateTimeViewModel : DateTimeViewModel by viewModels()

    private val baseURLQuotes = "https://zenquotes.io/"

    private lateinit var quotesClickable: CutCornerView
    private lateinit var floatingBtn : FloatingActionButton
    private lateinit var showQuotesBtn : TextView
    var quotesList = ArrayList<Quotes>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = homeBinding.root

        // Lifecycle
       lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                dateTimeViewModel.uiState.collect() {
                    //Update UI elements
                   // homeBinding.tvState.text = dateTimeViewModel.uiState.value.toString()
                }
            }
        }
        DateTimeViewModel().add()
        // Buttons

        floatingBtn = homeBinding.floatingActionButton
        quotesClickable = homeBinding.ccwQuotes
        quotesClickable.isVisible = false


        floatingBtn.setOnClickListener() {
            quotesClickable.isVisible = ! quotesClickable.isVisible
            YoYo.with(Techniques.BounceInLeft).duration(700).playOn(quotesClickable)

        }

        // Click listeners

        quotesClickable.setOnClickListener {
            getWisdomQuotes()
        }
        return view
    }

    // Fetch API Method
    private fun getWisdomQuotes() {
        val retroFit = Retrofit.Builder().baseUrl(baseURLQuotes)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val quotesAPI: QuotesAPI = retroFit.create(QuotesAPI::class.java)

        val call: retrofit2.Call<List<Quotes>> = quotesAPI.getWisdomQuote()

        // Callback function
        call.enqueue(object : Callback<List<Quotes>> {
            override fun onResponse(
                call: retrofit2.Call<List<Quotes>>,
                response: Response<List<Quotes>>
            ) {

                if (!response.isSuccessful) {
                    homeBinding.tvQuotesId.text = "Error - quote couldn't be found..."
                    homeBinding.tvAuthorId.text = "Error - author couldn't be found..."
                }

                quotesList = response.body() as ArrayList<Quotes>
                homeBinding.tvQuotesId.text = quotesList[0].q
                homeBinding.tvAuthorId.text = quotesList[0].a
                YoYo.with(Techniques.BounceInUp)
                    .duration(700)
                    .repeat(0)
                    .playOn(homeBinding.flTextFrame)

            }

            override fun onFailure(call: retrofit2.Call<List<Quotes>>, t: Throwable) {
                Toast.makeText(
                    activity,
                    t.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}




