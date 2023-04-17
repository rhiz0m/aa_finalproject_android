package com.taokyone.aa_finalproject_android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.taokyone.aa_finalproject_android.R
import com.taokyone.aa_finalproject_android.databinding.FragmentNotesBinding
import com.taokyone.aa_finalproject_android.model.NotesAdapter
import com.taokyone.aa_finalproject_android.model.UsersNotes


class NotesFragment : Fragment() {

    private lateinit var notesBinding: FragmentNotesBinding

    private val dataBase : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val referenceToGet : DatabaseReference = dataBase.reference.child("UsersNotes")

    val notesList = ArrayList<UsersNotes>()
    lateinit var notesAdapter : NotesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        notesBinding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        val view = notesBinding.root

        getDataFromFireBase()
        return view
    }

    private fun getDataFromFireBase() {
        referenceToGet.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                notesList.clear()

                for (eachNote in snapshot.children) {
                   val notes = eachNote.getValue(UsersNotes::class.java)

                    if (notes != null) {
                        notesList.add(notes)
                        // Debugging
                        /*
                        println(notes)
                        println("Users date: ${notes.date}")
                        println("Users quotes: ${notes.quotes}")
                        println("Users title: ${notes.title}")
                        println("Users reflection: ${notes.reflection}")
                        println("*************************************")*/


                    } else {
                        println("An error occurred...")
                    }

                    notesAdapter = NotesAdapter(this@NotesFragment, notesList)
                    notesBinding.rvNotesList.layoutManager = LinearLayoutManager(context)
                    notesBinding.rvNotesList.adapter = notesAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}