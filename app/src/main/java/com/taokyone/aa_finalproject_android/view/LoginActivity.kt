package com.taokyone.aa_finalproject_android.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.taokyone.aa_finalproject_android.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var signInBtn : Button
    private lateinit var signUpBtn : Button

    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)

        signInBtn =  loginBinding.btnSignin
        signUpBtn = loginBinding.btnSignupPage

        signInBtn.setOnClickListener() {

            val userEmail = loginBinding.etLoginUsermail.text.toString()
            val userPassword = loginBinding.etLoginPassword.text.toString()
            signInWithFirebase(userEmail, userPassword)
        }

        signUpBtn.setOnClickListener() {
            var intentSignUp = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intentSignUp)
        }
    }

    private fun signInWithFirebase (userEmail : String, userPassword : String) {

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    var intentSignIn = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intentSignIn)
                    finish()
                    Toast.makeText(applicationContext, "Sign in is successful!", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(applicationContext, "Please enter the right credentials", Toast.LENGTH_SHORT).show()

                }
            }
    }
}