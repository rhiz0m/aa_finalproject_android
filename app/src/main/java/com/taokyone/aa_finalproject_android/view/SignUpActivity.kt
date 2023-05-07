package com.taokyone.aa_finalproject_android.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.taokyone.aa_finalproject_android.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpBinding: ActivitySignUpBinding
    private lateinit var signUpBtn : Button
    private lateinit var cancelBtn : Button
    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = signUpBinding.root
        setContentView(view)

        signUpBtn = signUpBinding.btnSignup
        cancelBtn = signUpBinding.cancelBtn

        signUpBtn.setOnClickListener() {

            val userEmail = signUpBinding.etSignupUsermail.text.toString()
            val userPassword = signUpBinding.etSignupPassword.text.toString()
            signUpWithFirebase(userEmail, userPassword)

        }

        cancelBtn.setOnClickListener() {
            val intentCancel = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intentCancel)
        }


    }

    private fun signUpWithFirebase (userEmail: String, userPassword : String) {
        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener() {task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "Your account has been created", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(applicationContext, task.exception?.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}