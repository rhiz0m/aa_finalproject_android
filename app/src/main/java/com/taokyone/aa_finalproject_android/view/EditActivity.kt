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
        val title = intent.getStringExtra("title")
        val date = intent.getIntExtra("date", 0).toString()
        val reflections = intent.getStringExtra("reflections")
        val nasaData = intent.getStringExtra("nasaData")

        // Set the data
        editBinding.etTitle.setText(title)
        editBinding.etDate.setText(date)
        editBinding.etReflections.setText(reflections)
        editBinding.tvApiInfo.text = nasaData


    }

    private fun updateData () {
        val titleUpdated = editBinding.etTitle.text.toString()
        val dateUpdated = editBinding.etDate.text.toString().toInt()
        val reflectionsUpdated = editBinding.etReflections.text.toString()
        val nasaDataUpdated = editBinding.tvApiInfo.text.toString()

        // Getting the Id from NotesAdapter
        val uniqueId = intent.getStringExtra("uniqueId").toString()

        // Map from Firebase to get the key/value pair. Any = either String or INt
        val notesMap = mutableMapOf<String, Any>()
        notesMap["uniqueId"] = uniqueId
        notesMap["title"] = titleUpdated
        notesMap["date"] = dateUpdated
        notesMap["reflections"] = reflectionsUpdated
        notesMap["nasaData"] = nasaDataUpdated

        myReference.child(uniqueId).updateChildren(notesMap).addOnCompleteListener() {task ->
            
            if(task.isSuccessful) {
                Toast.makeText(applicationContext, "Your note have been updated!", Toast.LENGTH_SHORT).show()

            }
        }
     }
}