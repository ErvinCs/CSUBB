package com.lab021.csoka.sweater

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {

    lateinit var buttonHosted: Button
    lateinit var buttonSubscribed: Button
    lateinit var textName: TextView
    //image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_view)

        buttonHosted = findViewById(R.id.buttonHosted)
        buttonSubscribed = findViewById(R.id.buttonSubscribed)
        textName = findViewById(R.id.textName)

        buttonHosted.setOnClickListener {
            //TODO
            startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
        }

        buttonSubscribed.setOnClickListener {
            //TODO
            startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
        }
    }
}