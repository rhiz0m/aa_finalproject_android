package com.taokyone.aa_finalproject_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.taokyone.aa_finalproject_android.R
import com.taokyone.aa_finalproject_android.databinding.ActivityListBinding
import com.taokyone.aa_finalproject_android.model.NotesAdapter
import com.taokyone.aa_finalproject_android.model.UserNotes

class ListActivity : AppCompatActivity() {

    private lateinit var listBinding: ActivityListBinding

    private val dataBase : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val referenceToGet : DatabaseReference = dataBase.reference.child("UsersNotes")

    private lateinit var notesAdapter : NotesAdapter
    private val notesList = ArrayList<UserNotes>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

         listBinding = ActivityListBinding.inflate(layoutInflater)
        val view = listBinding.root
        setContentView(view)

        getDataFromFireBase()

    }
    private fun getDataFromFireBase() {
        referenceToGet.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                notesList.clear()

                for (eachNote in snapshot.children) {
                    val notes = eachNote.getValue(UserNotes::class.java)

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
                    notesAdapter = NotesAdapter(this@ListActivity, notesList)
                    listBinding.rvNotesList.layoutManager = LinearLayoutManager(this@ListActivity)
                    listBinding.rvNotesList.adapter = notesAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}