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

class EditEventActivity : AppCompatActivity() {
    final val EXTRA_POST_REPLY = "com.example.csoka.lab_02_android.POST_REPLY"
    final val DELETE_POST_REPLY = "com.example.csoka.lab_02_android.DELETE_REPLY"

    lateinit var editName : EditText
    lateinit var editMemberLimit : EditText
    lateinit var editDate : EditText
    lateinit var editTime : EditText
    lateinit var editLocation : EditText
    lateinit var editDescription : EditText
    lateinit var updateButton : Button
    lateinit var deleteButton : Button
    lateinit var oldPostId : TextView
    lateinit var oldPost : Post

    lateinit var firebaseAuth : FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_post)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("posts")

        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)

        editName = findViewById(R.id.editName)
        editMemberLimit = findViewById(R.id.editMemberLimit)
        editDate = findViewById(R.id.editDate)
        editTime = findViewById(R.id.editTime)
        editLocation = findViewById(R.id.editLocation)
        editDescription = findViewById(R.id.editDescription)
        updateButton = findViewById(R.id.updateButton)

        oldPost = intent.extras.getParcelable<Post>(PostListAdapter.intentMsg)

        editName.setText(oldPost.activityName)
        editMemberLimit.setText(oldPost.memberLimit.toString())
        editDate.setText(oldPost.date)
        editTime.setText(oldPost.time)
        editLocation.setText(oldPost.location)
        editDescription.setText(oldPost.description)

        updateButton.setOnClickListener { updateActivity() }
        deleteButton.setOnClickListener { deleteActivity() }
    }

    private fun updateActivity() {

        val replyIntent : Intent = Intent()
        if (editName.text.isEmpty() || editMemberLimit.text.isEmpty() ||
            editDate.text.isEmpty() || editTime.text.isEmpty() ||
            editLocation.text.isEmpty()) {
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {
            val post = Post(firebaseAuth.currentUser!!.email, editName.text.toString(),
                editMemberLimit.text.toString().toInt(), editDate.text.toString(),
                editTime.text.toString(), editLocation.text.toString())
            post.firebase_id = oldPost.firebase_id
            post.description = editDescription.text.toString()
            post.id = 0 //oldPost.id - Deprecated
            replyIntent.putExtra(EXTRA_POST_REPLY, post)
            setResult(Activity.RESULT_OK, replyIntent)
        }
        finish()
    }

    private fun deleteActivity() {
        deleteButton = findViewById(R.id.deleteButton)
        val replyIntent : Intent = Intent()
        replyIntent.putExtra(DELETE_POST_REPLY, oldPost)
        setResult(Activity.RESULT_OK, replyIntent)
        finish()
    }
}