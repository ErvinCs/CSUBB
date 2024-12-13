package com.lab021.csoka.sweater.authActivities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.lab021.csoka.sweater.MainActivity
import com.lab021.csoka.sweater.R

class LoginActivity : AppCompatActivity() {

    final val EXTRA_LOGIN_REPLY = "com.example.csoka.lab_02_android.LOGIN_REPLY"

    lateinit var editEmail : EditText
    lateinit var editPassword : EditText
    lateinit var loginButton : Button
    lateinit var textForgotPassword : TextView
    lateinit var textNotRegistered : TextView

    lateinit var firebaseAuth : FirebaseAuth
    //TODO - ProgressBar
    lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_view)

        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }

        //Init ProgressBar

        loginButton = findViewById(R.id.loginButton)
        loginButton.setOnClickListener { loginUser() }

        textForgotPassword = findViewById(R.id.forgotPassword)
        textForgotPassword.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }
        textNotRegistered = findViewById(R.id.textNotRegistered)
        textNotRegistered.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        editEmail = findViewById(R.id.editEmailLogin)
        editPassword = findViewById(R.id.editPasswordLogin)

    }

    fun loginUser() {
        val email = editEmail.text.toString().trim()
        val password = editPassword.text.toString().trim()

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "E-mail is empty", Toast.LENGTH_LONG).show()
            return
        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_LONG).show()
            return
        }

        //ProgressBar while logging in

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    Log.d("LoginActivity: ", "Successful Login")
                    updateUIOnLogin()
                } else {
                    Log.d("LoginActivity: ", "Authentication failed")
                    Toast.makeText(this@LoginActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun updateUIOnLogin() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}