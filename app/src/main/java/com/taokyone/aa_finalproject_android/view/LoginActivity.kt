package com.taokyone.aa_finalproject_android.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.taokyone.aa_finalproject_android.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var signInBtn : Button
    private lateinit var signUpBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)

        signInBtn =  loginBinding.btnSignin
        signUpBtn = loginBinding.btnSignupPage

        signInBtn.setOnClickListener() {
            var intentSignIn = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intentSignIn)
        }

        signUpBtn.setOnClickListener() {
            var intentSignUp = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intentSignUp)
        }
    }
}