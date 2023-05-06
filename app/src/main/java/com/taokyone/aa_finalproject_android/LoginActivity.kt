package com.taokyone.aa_finalproject_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.taokyone.aa_finalproject_android.databinding.ActivityLoginBinding
import com.taokyone.aa_finalproject_android.view.ListActivity
import com.taokyone.aa_finalproject_android.view.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var signInBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)

        signInBtn =  loginBinding.signinBtn

        signInBtn.setOnClickListener() {
            var intentRecycler = Intent(this, MainActivity::class.java)
            startActivity(intentRecycler)
        }
    }
}