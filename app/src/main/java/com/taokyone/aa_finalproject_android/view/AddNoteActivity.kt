package com.taokyone.aa_finalproject_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.taokyone.aa_finalproject_android.R
import com.taokyone.aa_finalproject_android.databinding.ActivityAddNoteBinding
import com.taokyone.aa_finalproject_android.model.Nasa
import com.taokyone.aa_finalproject_android.model.UserNotes
import com.taokyone.aa_finalproject_android.model.apiData.NasaAPI
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.time.LocalDate

class AddNoteActivity : AppCompatActivity() {

    lateinit var addNotesBinding : ActivityAddNoteBinding
    private val firebaseDb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val reference : DatabaseReference = firebaseDb.reference.child("UsersNotes")

    private lateinit var showUsersNotesBtn : Button
    private val baseURLNasa = "https://api.nasa.gov/"
    private lateinit var showDate : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        addNotesBinding = ActivityAddNoteBinding.inflate(layoutInflater)
        val view = addNotesBinding.root
        setContentView(view)

        supportActionBar?.title = "Add Note"

        showUsersNotesBtn = addNotesBinding.btnUpdate

        showDate = addNotesBinding.tvDateAdd

        showDate.text = LocalDate.now().toString()

        showUsersNotesBtn.setOnClickListener() {
            // Form validation
            /*
            val title: String = addNotesBinding.etTitle.text.toString().trim()
            val category: String = addNotesBinding.etCategory.text.toString().trim()
            val reflections = addNotesBinding.etReflections.text.toString().trim()

            if(title.isEmpty()){
                Toast.makeText(applicationContext, "Please enter a title", Toast.LENGTH_SHORT).show()
            } else if (category.isEmpty()) {
                Toast.makeText(applicationContext, "Please type a category", Toast.LENGTH_SHORT).show()
            } else if (reflections.isEmpty()) {
                Toast.makeText(applicationContext, "Please type a reflection", Toast.LENGTH_SHORT).show()
            }
            else { */
                addNoteEntity()
                finish()
        }
    }

    private fun addNoteEntity() {

        val date : String = LocalDate.now().toString()
        val title : String = addNotesBinding.etTitle.text.toString()
        val category: String = addNotesBinding.etCategory.text.toString()
        val reflections : String = addNotesBinding.etReflections.text.toString()


        //Creating a unique key for each note
        val uniqueId : String = reference.push().key.toString()
        //Notes-object to Firebase
        val notesObj = UserNotes(uniqueId, date, title, category, reflections)

        reference.child(uniqueId).setValue(notesObj).addOnCompleteListener() { task ->

            if (task.isSuccessful) {
                Toast.makeText(this, "The Notes got added to Firebase!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}