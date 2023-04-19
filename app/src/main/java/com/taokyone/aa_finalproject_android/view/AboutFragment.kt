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

    // ViewBinding
    private lateinit var viewBinding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Setup viewBinding

        viewBinding = FragmentAboutBinding.inflate(layoutInflater,  container, false)
        val view = viewBinding.root

        return view
    }

}