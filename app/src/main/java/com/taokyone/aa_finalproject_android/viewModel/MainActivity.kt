package com.taokyone.aa_finalproject_android.viewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.navigation.NavigationBarView
import com.taokyone.aa_finalproject_android.R
import com.taokyone.aa_finalproject_android.databinding.ActivityMainBinding
import com.taokyone.aa_finalproject_android.view.*
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private val dateTimeViewModel : DateTimeViewModel by viewModels()
    // Create an object from Firebase reference class
    /*
    private val dataBase : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val referenceToSend : DatabaseReference = dataBase.reference.child("Users")
    private val referenceToGet : DatabaseReference = dataBase.reference

    // Variables
    lateinit var sendDataBtn: Button
    private lateinit var enterData: EditText
    private lateinit var receiveData: TextView
*/


    lateinit var showDateBtn: Button

    // ViewBinding
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        showDateBtn = viewBinding.btnTop


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
        }



        // OnItemListener for the Bottom Navigation
        viewBinding.bottomNavView.setOnItemSelectedListener(this)

        // Firebase DB Test
        /*
        sendDataBtn = viewBinding.btnTop
        enterData = viewBinding.tvEnterData
        receiveData = viewBinding.tvIncomingData

        referenceToGet.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               val getName : String = snapshot.child("Users").child("name").value as String
                receiveData.text = getName
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        // Send data to Firebase DB
        sendDataBtn.setOnClickListener() {
            val userName = enterData.text.toString()
            referenceToSend.child("name").setValue(userName)
        } */
    }

    // Fragment section
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
            replace(R.id.nav_host_fragment, AddNoteFragment())
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
