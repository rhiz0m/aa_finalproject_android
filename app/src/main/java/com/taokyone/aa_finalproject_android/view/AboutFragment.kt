package com.taokyone.aa_finalproject_android.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.taokyone.aa_finalproject_android.databinding.FragmentAboutBinding
import com.taokyone.aa_finalproject_android.viewModel.DateTimeViewModel
import io.github.florent37.shapeofview.shapes.BubbleView
import kotlinx.coroutines.launch


class AboutFragment : Fragment() {

    // ViewBinding
    private lateinit var aboutBinding: FragmentAboutBinding
    private lateinit var aboutBtn : FloatingActionButton
    private lateinit var aboutBubble : BubbleView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        aboutBinding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        val view = aboutBinding.root
        // Id
        aboutBtn = aboutBinding.fabAbout
        aboutBubble = aboutBinding.ccwAbout
        aboutBubble.isVisible = false

            aboutBtn.setOnClickListener() {
                aboutBubble.isVisible = ! aboutBubble.isVisible
                YoYo.with(Techniques.BounceInLeft).duration(700).playOn(aboutBubble)
            }


        // Setup viewBinding

        aboutBinding = FragmentAboutBinding.inflate(layoutInflater,  container, false)

        return view
    }
}

