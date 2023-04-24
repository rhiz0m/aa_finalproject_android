package com.taokyone.aa_finalproject_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.taokyone.aa_finalproject_android.R
import com.taokyone.aa_finalproject_android.databinding.ActivityEditBinding
import com.taokyone.aa_finalproject_android.model.NotesAdapter

class EditActivity : AppCompatActivity() {

    private val firebaseDb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference : DatabaseReference = firebaseDb.reference.child("UsersNotes")

    lateinit var editBinding: ActivityEditBinding
    private lateinit var updateData : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editBinding = ActivityEditBinding.inflate(layoutInflater)
        val view = editBinding.root
        setContentView(view)

        supportActionBar?.title = "Edit data"

        updateData = editBinding.btnUpdate

        // Update Data Listener
        updateData.setOnClickListener() {
            updateData()
            finish()
        }

        getAndEditData()

    }

    private fun getAndEditData() {
        // Get the data from NotesAdapter
        val date = intent.getStringExtra("date")
        val title = intent.getStringExtra("title")
        val category = intent.getStringExtra("category")
        val reflections = intent.getStringExtra("reflections")


        // Set the data
        editBinding.tvDateAdd.text = date
        editBinding.etTitle.setText(title)
        editBinding.etCategory.setText(category)
        editBinding.etReflections.setText(reflections)
    }

    private fun updateData () {

        // Getting the Id from NotesAdapter
        val uniqueId = intent.getStringExtra("uniqueId").toString()

        val dateUpdated = editBinding.tvDateAdd.text.toString()
        val titleUpdated = editBinding.etTitle.text.toString()
        val categoryUpdated = editBinding.etCategory.text.toString()
        val reflectionsUpdated = editBinding.etReflections.text.toString()

        // Map from Firebase to get the key/value pair. Any = either String or INt
        val notesMap = mutableMapOf<String, Any>()
        notesMap["uniqueId"] = uniqueId
        notesMap["date"] = dateUpdated
        notesMap["title"] = titleUpdated
        notesMap["category"] = categoryUpdated
        notesMap["reflections"] = reflectionsUpdated


        myReference.child(uniqueId).updateChildren(notesMap).addOnCompleteListener() {task ->
            
            if(task.isSuccessful) {
                Toast.makeText(applicationContext, "Your note have been updated!", Toast.LENGTH_SHORT).show()

            }
        }
     }
}