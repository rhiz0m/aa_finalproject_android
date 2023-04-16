package com.taokyone.aa_finalproject_android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.taokyone.aa_finalproject_android.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding


    private val firebaseDb : FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference : DatabaseReference = firebaseDb.reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = homeBinding.root



        return view
    }

}




