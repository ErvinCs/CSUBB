package com.lab021.csoka.sweater.crudActivities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.lab021.csoka.sweater.R
import com.lab021.csoka.sweater.model.Post

//TODO - id - deprecated
class NewEventActivity : AppCompatActivity() {
    final val postsUrl = "http://10.0.2.2:8080/posts" //"http://127.0.0.1:8080/posts"

    final val EXTRA_POST_REPLY = "com.example.csoka.lab_02_android.POST_REPLY"

    lateinit var editName : EditText
    lateinit var editMemberLimit : EditText
    lateinit var editDate : EditText
    lateinit var editTime : EditText
    lateinit var editLocation : EditText
    lateinit var editDescription : EditText
    lateinit var createButton : Button

    lateinit var firebaseAuth : FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        databaseReference = FirebaseDatabase.getInstance().getReference("posts");
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()


        createButton = findViewById(R.id.createButton)
        createButton.setOnClickListener { createActivity()}
    }

    private fun createActivity() {
        editName = findViewById(R.id.editName)
        editMemberLimit = findViewById(R.id.editMemberLimit)
        editDate = findViewById(R.id.editDate)
        editTime = findViewById(R.id.editTime)
        editLocation = findViewById(R.id.editLocation)
        editDescription = findViewById(R.id.editDescription)
        createButton = findViewById(R.id.createButton)

        val replyIntent : Intent = Intent()
        if (editName.text.isEmpty() || editMemberLimit.text.isEmpty() ||
            editDate.text.isEmpty() || editTime.text.isEmpty() ||
            editLocation.text.isEmpty()) {
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {
            val post = Post(
                firebaseAuth.currentUser!!.email, editName.text.toString(),
                editMemberLimit.text.toString().toInt(), editDate.text.toString(),
                editTime.text.toString(), editLocation.text.toString()
            )
            val firebase_id = databaseReference.push().key
            post.firebase_id = firebase_id
            post.description = editDescription.text.toString()
            post.id = 0 //Deprecated

            replyIntent.putExtra(EXTRA_POST_REPLY, post)
            setResult(Activity.RESULT_OK, replyIntent)
        }
        finish()
    }
}