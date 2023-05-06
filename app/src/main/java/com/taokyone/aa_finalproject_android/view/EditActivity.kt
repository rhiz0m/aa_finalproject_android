package com.taokyone.aa_finalproject_android.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.navigation.Navigation
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.taokyone.aa_finalproject_android.R
import com.taokyone.aa_finalproject_android.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private val firebaseDb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val reference : DatabaseReference = firebaseDb.reference.child("UsersNotes")

    lateinit var editBinding: ActivityEditBinding
    private lateinit var updateData : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editBinding = ActivityEditBinding.inflate(layoutInflater)
        val view = editBinding.root
        setContentView(view)

        supportActionBar?.title = "Edit data"

        // Buttons

        updateData = editBinding.btnEdit

        // Click Listeners

        updateData.setOnClickListener() {
            // Form validation

            val title: String = editBinding.etTitle.text.toString().trim()
            val category: String = editBinding.etCategory.text.toString().trim()
            val reflections = editBinding.etReflections.text.toString().trim()

            if (title.isEmpty()) {
                editBinding.etTitle.background = ContextCompat.getDrawable(applicationContext, R.color.custom_teal_200)
                YoYo.with(Techniques.Flash).repeat(0).playOn(editBinding.etTitle)
                Toast.makeText(applicationContext, "Please enter a title", Toast.LENGTH_SHORT)
                    .show()
            } else if (category.isEmpty()) {
                editBinding.etCategory.background = ContextCompat.getDrawable(applicationContext, R.color.custom_teal_200)
                YoYo.with(Techniques.Flash).repeat(0).playOn(editBinding.etCategory)
                Toast.makeText(applicationContext, "Please type a category", Toast.LENGTH_SHORT)
                    .show()
            } else if (reflections.isEmpty()) {
                editBinding.etReflections.background = ContextCompat.getDrawable(applicationContext, R.color.custom_teal_200)
                YoYo.with(Techniques.Flash).repeat(0).playOn(editBinding.etReflections)
                Toast.makeText(applicationContext, "Please type a reflection", Toast.LENGTH_SHORT)
                    .show()
            } else {
                updateData()
                finish()
            }
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


        reference.child(uniqueId).updateChildren(notesMap).addOnCompleteListener() { task ->
            
            if(task.isSuccessful) {
                Toast.makeText(applicationContext, "Your note have been updated!", Toast.LENGTH_SHORT).show()

            }
        }
     }
}