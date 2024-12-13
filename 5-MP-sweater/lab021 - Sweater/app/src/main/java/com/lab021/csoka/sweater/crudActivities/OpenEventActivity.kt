package com.lab021.csoka.sweater.crudActivities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.lab021.csoka.sweater.R
import com.lab021.csoka.sweater.model.Post
import com.lab021.csoka.sweater.ui.PostListAdapter

class OpenEventActivity : AppCompatActivity() {

    final val postsUrl = "http://10.0.2.2:8080/posts" //"http://127.0.0.1:8080/posts"

    final val EXTRA_OPEN_REPLY = "com.example.csoka.lab_02_android.OPEN_REPLY"

    lateinit var tvName : TextView
    lateinit var tvLimit : TextView
    lateinit var tvDate : TextView
    lateinit var tvTime : TextView
    lateinit var tvLocation : TextView
    lateinit var tvDescription : TextView
    lateinit var post : Post
    lateinit var goingButton : Button

    lateinit var firebaseAuth : FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_event)

        databaseReference = FirebaseDatabase.getInstance().getReference("posts");
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()

        post = intent.extras.getParcelable<Post>(PostListAdapter.intentMsg)

        tvName = findViewById(R.id.tvName)
        tvName.setText(post.activityName)
        tvLimit = findViewById(R.id.tvLimit)
        tvLimit.setText("Going: 1/" + post.memberLimit.toString())
        tvDate = findViewById(R.id.tvDate)
        tvDate.setText(post.date.toString())
        tvTime = findViewById(R.id.tvTime)
        tvTime.setText(post.time.toString())
        tvLocation = findViewById(R.id.tvLocation)
        tvLocation.setText(post.location)
        tvDescription = findViewById(R.id.tvDescription)
        tvDescription.setText(post.description)

        goingButton = findViewById(R.id.goingButton)
        goingButton.setOnClickListener { saveToGoing()}
    }

    private fun saveToGoing() {
        var replyIntent : Intent = Intent()
        replyIntent.putExtra(EXTRA_OPEN_REPLY, true)
        setResult(Activity.RESULT_OK, replyIntent)
        finish()
    }

}