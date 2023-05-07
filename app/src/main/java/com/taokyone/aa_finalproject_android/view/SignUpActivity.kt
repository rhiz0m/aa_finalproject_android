package com.taokyone.aa_finalproject_android.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.taokyone.aa_finalproject_android.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpBinding: ActivitySignUpBinding
    private lateinit var signUpBtn : Button
    private lateinit var cancelBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = signUpBinding.root
        setContentView(view)

        signUpBtn = signUpBinding.btnSignup
        cancelBtn = signUpBinding.cancelBtn

        signUpBtn.setOnClickListener() {

        }

        cancelBtn.setOnClickListener() {
            val intentCancel = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intentCancel)
        }
    }
}