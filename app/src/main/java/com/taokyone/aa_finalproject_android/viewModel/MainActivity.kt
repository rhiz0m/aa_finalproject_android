package com.taokyone.aa_finalproject_android.viewModel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.taokyone.aa_finalproject_android.R
import com.taokyone.aa_finalproject_android.databinding.ActivityMainBinding
import com.taokyone.aa_finalproject_android.view.*


class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private val dateTimeViewModel : DateTimeViewModel by viewModels()

    private lateinit var showDateBtn: Button
    private lateinit var addNotes: Button

    // ViewBinding
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        addNotes = viewBinding.btnAdd
        var intentAddNote = Intent(this, AddNoteActivity::class.java)

        addNotes.setOnClickListener() {
            startActivity(intentAddNote)

        }


        /*
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dateTimeViewModel.uiState.collect() {
                    //Update UI elements
                    viewBinding.tvDate.text = dateTimeViewModel.uiState.value.toString()

                }
            }
        }

        showDateBtn.setOnClickListener() {
            dateTimeViewModel.add()

        } */

        // OnItemListener for the Bottom Navigation
        viewBinding.bottomNavView.setOnItemSelectedListener(this)
    }

    // Fragment Bottom section
    private fun onHomeClicked () {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainerView, HomeFragment())
        }
    }
     private fun onNotesClicked () {
         var intentRecycler = Intent(this, ListActivity::class.java)
         startActivity(intentRecycler)
    }

    private fun onNasaClicked () {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainerView, NasaFragment())
        }
    }

     private fun onAboutClicked () {
         supportFragmentManager.commit {
             replace(R.id.fragmentContainerView, AboutFragment())
         }
    }


    // Setting
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_home -> {
                onHomeClicked()
                true
            }
            R.id.nav_notes -> {
                onNotesClicked()
                true
            }
            R.id.nav_nasa -> {
                onNasaClicked()
                true
            }

            R.id.nav_about -> {
                onAboutClicked()
                true
            }
            else -> {
                false
            }
        }
    }
}
