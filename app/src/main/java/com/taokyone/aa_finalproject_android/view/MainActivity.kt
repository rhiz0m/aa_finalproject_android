package com.taokyone.aa_finalproject_android.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.taokyone.aa_finalproject_android.R
import com.taokyone.aa_finalproject_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var logoutImg: ImageView

    // ViewBinding
    private lateinit var viewBinding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        logoutImg = viewBinding.btnLogout

        logoutImg.setOnClickListener() {
            var intentRecycler = Intent(this, LoginActivity::class.java)
            startActivity(intentRecycler)

        }

        // OnItemListener for the Bottom Navigation
        viewBinding.bottomNavView.setOnItemSelectedListener(this)
    }

    // Fragment Bottom section
    private fun onHomeClicked () {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainerView, HomeFragment())
        }
    }

    private fun onNasaClicked () {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainerView, NasaFragment())
        }
    }
    private fun onListClicked () {
        var intentRecycler = Intent(this, ListActivity::class.java)
        startActivity(intentRecycler)
    }

    private fun onAddClicked () {
        var intentAddNote = Intent(this, AddNoteActivity::class.java)
        startActivity(intentAddNote)
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
            R.id.nav_nasa -> {
                onNasaClicked()
                true
            }
            R.id.nav_notes -> {
                onListClicked()
                true
            }
            R.id.nav_addnote -> {
                onAddClicked()
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
