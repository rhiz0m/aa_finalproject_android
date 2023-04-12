package com.taokyone.aa_finalproject_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.taokyone.aa_finalproject_android.R
import com.taokyone.aa_finalproject_android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    // ViewBinding
    private lateinit var viewBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        // OnItemListener for the Bottom Navigation
        viewBinding.bottomNavView.setOnItemSelectedListener(this)

    }

    private fun onHomeClicked () {
        supportFragmentManager.commit {
            replace(R.id.nav_host_fragment, HomeFragment())
        }
    }
    private fun onNotesClicked () {
        supportFragmentManager.commit {
            replace(R.id.nav_host_fragment, NotesFragment())
        }
    }
    private fun onExploreClicked () {
        supportFragmentManager.commit {
            replace(R.id.nav_host_fragment, ExploreFragment())
        }
    }
    private fun onAboutClicked () {
        supportFragmentManager.commit {
            replace(R.id.nav_host_fragment, AboutFragment())
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
            R.id.nav_explore -> {
                onExploreClicked()
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
