package com.taokyone.aa_finalproject_android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taokyone.aa_finalproject_android.databinding.FragmentAddNoteBinding


class AddNoteFragment : Fragment() {

    lateinit var addNotesBinding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       addNotesBinding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        val view = addNotesBinding.root
        return view
    }

}

