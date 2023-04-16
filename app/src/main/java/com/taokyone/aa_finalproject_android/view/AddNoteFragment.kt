package com.taokyone.aa_finalproject_android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.taokyone.aa_finalproject_android.databinding.FragmentAddNoteBinding
import com.taokyone.aa_finalproject_android.model.UsersNotes
import com.taokyone.aa_finalproject_android.model.NasaImage
import com.taokyone.aa_finalproject_android.model.apiData.NasaImageAPI
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class AddNoteFragment : Fragment() {

    lateinit var addNotesBinding: FragmentAddNoteBinding
    private val firebaseDb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference : DatabaseReference = firebaseDb.reference.child("UsersNotes")

    private lateinit var showUsersNotesBtn : Button
    private val baseURLNasa = "https://api.nasa.gov/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       addNotesBinding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        val view = addNotesBinding.root

        showUsersNotesBtn = addNotesBinding.btnUpdate

        showUsersNotesBtn.setOnClickListener() {
            addNoteEntity()
        }

        fun getNasaUrl () {

            val retroFit = Retrofit.Builder().
            baseUrl(baseURLNasa).
            addConverterFactory(GsonConverterFactory.create())
                .build()

            val nasaImgAPI = retroFit.create<NasaImageAPI>().getNasaImage()

            nasaImgAPI.enqueue(object : Callback<NasaImage> {
                override fun onResponse(call: retrofit2.Call<NasaImage>, response: Response<NasaImage>) {

                    val nasaImgUrl = response.body()

                    if (response.isSuccessful) {
                        addNotesBinding.tvQuotes.text = "$nasaImgUrl"
                    }
                }

                override fun onFailure(call: retrofit2.Call<NasaImage>, t: Throwable) {
                    Toast.makeText(activity,
                        t.localizedMessage,
                        Toast.LENGTH_LONG).show()
                }

            })
        }

        addNotesBinding.tvQuotes.text = getNasaUrl().toString()

        return view
    }

    private fun addNoteEntity() {
        val quotes: String = addNotesBinding.tvQuotes.text.toString()
        val title : String = addNotesBinding.etTitle.text.toString()
        val date: Int = addNotesBinding.etdDate.text.toString().toInt()
        val reflection : String = addNotesBinding.etMultiDescription.text.toString()


        //Creating a unique key for each note

        val uniqueId : String = myReference.push().key.toString()
        //Notes-object to Firebase
        val notesObj = UsersNotes(quotes,title, date, reflection)

        myReference.child(uniqueId).setValue(notesObj).addOnCompleteListener() {task ->

            if (task.isSuccessful) {
                Toast.makeText(activity, "The Notes got Added to Firebase!", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(activity, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }

        }

    }


}

