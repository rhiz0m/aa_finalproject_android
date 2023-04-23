package com.taokyone.aa_finalproject_android.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.taokyone.aa_finalproject_android.databinding.FragmentAboutBinding
import com.taokyone.aa_finalproject_android.viewModel.DateTimeViewModel
import com.taokyone.aa_finalproject_android.viewModel.QuotesViewModel
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

        // Clock State handler

        fun clock() {
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed(object : Runnable {
                override fun run() {
                    dateTimeViewModel.add()
                    handler.postDelayed(this, 1000)//1 sec delay
                }
            }, 0)
        }

        val clockView = viewBinding.tvLocalTime



        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                dateTimeViewModel.uiState.collect() {
                    //Update UI elements
                    viewBinding.tvLocalTime.text = "Time: " + dateTimeViewModel.uiState.value.time.toString()
                    viewBinding.tvLocalDate.text = "Date:" + dateTimeViewModel.uiState.value.date.toString()
                }
            }
        }
        clock()
        YoYo.with(Techniques.FadeInLeft).duration(6000).repeat(100).playOn(clockView)
        return view
    }
}

