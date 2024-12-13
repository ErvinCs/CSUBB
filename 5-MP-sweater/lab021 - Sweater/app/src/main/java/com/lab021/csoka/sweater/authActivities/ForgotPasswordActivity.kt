package com.lab021.csoka.sweater.authActivities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.lab021.csoka.sweater.R

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var resetButton: Button

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password_view)

        firebaseAuth = FirebaseAuth.getInstance()

        editEmail = findViewById(R.id.editEmailForgot)
        resetButton = findViewById(R.id.resetButton)
        resetButton.setOnClickListener { resetPassword() }

    }

    fun resetPassword() {
        val email = editEmail.text.toString().trim()

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "E-mail is empty", Toast.LENGTH_LONG).show()
            return
        }

        firebaseAuth
            .sendPasswordResetEmail(email)
            .addOnCompleteListener{ task ->
            if(task.isSuccessful) {
                Log.d("ForgotPasswordActivity:", "E-mail sent")
                Toast.makeText(this, "E-mail sent", Toast.LENGTH_SHORT).show()
                updateUI()
            } else {
                Log.d("ForgotPasswordActivity:", "Failed to send e-mail")
                Toast.makeText(this, "Invalid e-mail address", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateUI() {
        val intent = Intent(this@ForgotPasswordActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}