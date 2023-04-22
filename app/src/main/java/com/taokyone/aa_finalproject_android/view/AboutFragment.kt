package com.taokyone.aa_finalproject_android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taokyone.aa_finalproject_android.databinding.FragmentAboutBinding


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

