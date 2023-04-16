package com.taokyone.aa_finalproject_android.view

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.taokyone.aa_finalproject_android.R
import com.taokyone.aa_finalproject_android.databinding.FragmentNotesBinding


class NotesFragment : Fragment() {

    private lateinit var notesBinding: FragmentNotesBinding
    lateinit var notesBtn : FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        notesBinding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        val view = notesBinding.root


        notesBtn = notesBinding.fabAddNotes
        notesBtn.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.action_notesFragment_to_homeFragment)
        }

        return view
    }


}