package com.lab021.csoka.sweater.authActivities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.lab021.csoka.sweater.MainActivity
import com.lab021.csoka.sweater.R

class RegisterActivity : AppCompatActivity() {
    final val EXTRA_REGISTER_REPLY = "com.example.csoka.lab_02_android.REGISTER_REPLY"

    lateinit var editEmail : EditText
    lateinit var editPassword : EditText
    lateinit var registerButton : Button
    lateinit var textAlreadyRegistered : TextView

    lateinit var firebaseAuth : FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    //TODO - ProgressBar
    lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_view)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("Users")
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
        }

        //Init ProgressBar

        registerButton = findViewById(R.id.registerButton)
        registerButton.setOnClickListener { registerUser() }

        textAlreadyRegistered = findViewById(R.id.textAlreadyRegistered)
        textAlreadyRegistered.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
    }

    fun registerUser() {
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

        //ProgressBar while registering

        firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                //Hide ProgressBar
                if(task.isSuccessful) {
                    Log.d("RegisterActivity", "Successful registration")

                    val userId = firebaseAuth.currentUser!!.uid

                    //TODO - Verify e-mail

                    //TODO - ProgileInformation - databaseReference

                    updateUIOnRegistering()
                } else {
                    Log.d("RegisterActivity", "Registering failed")
                    Toast.makeText(this, "Registering failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun updateUIOnRegistering() {
        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}