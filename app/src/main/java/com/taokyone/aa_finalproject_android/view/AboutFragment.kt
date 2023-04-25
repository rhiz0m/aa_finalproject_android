package com.taokyone.aa_finalproject_android.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.taokyone.aa_finalproject_android.databinding.FragmentAboutBinding
import com.taokyone.aa_finalproject_android.viewModel.DateTimeViewModel
import kotlinx.coroutines.launch


class AboutFragment : Fragment() {

    // ViewModel
    private val dateTimeViewModel : DateTimeViewModel by viewModels()

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

