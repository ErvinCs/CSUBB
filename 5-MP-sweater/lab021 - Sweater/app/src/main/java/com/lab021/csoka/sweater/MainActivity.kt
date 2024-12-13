package com.lab021.csoka.sweater

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.lab021.csoka.sweater.authActivities.LoginActivity
import com.lab021.csoka.sweater.crudActivities.NewEventActivity
import com.lab021.csoka.sweater.model.Post
import com.lab021.csoka.sweater.ui.PostListAdapter
import com.lab021.csoka.sweater.ui.PostViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    final val NEW_POST_ACTIVITY_CODE = 1
    final val UPDATE_POST_CODE = 2
    final val OPEN_POST_CODE = 3

    final val EXTRA_POST_REPLY = "com.example.csoka.lab_02_android.POST_REPLY"
    final val DELETE_POST_REPLY = "com.example.csoka.lab_02_android.DELETE_REPLY"
    final val EXTRA_OPEN_REPLY = "com.example.csoka.lab_02_android.OPEN_REPLY"

    lateinit var postViewModel : PostViewModel
    lateinit var adapter : PostListAdapter

    lateinit var firebaseAuth : FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    lateinit var tempPostList : ArrayList<Post>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference

        if (firebaseAuth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        var recyclerView : RecyclerView = findViewById(R.id.main_recyclerview)
        adapter = PostListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Get the ViewModel
        postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)

        tempPostList = ArrayList<Post>()
        val listener = object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                tempPostList.clear()

                for(snapshot : DataSnapshot  in data.children) {
                    val post : Post? = snapshot.getValue(Post::class.java)
                    if(post != null)
                        tempPostList.add(post)
                }

                adapter.setPosts(tempPostList.toList())
            }
            override fun onCancelled(data: DatabaseError) {
               Log.d("MainActivity: ", "DataChange failed")
            }
        }
        databaseReference.addValueEventListener(listener)

        //Add an observer for the LiveData
//        postViewModel.allPosts.observe(this, object : Observer<List<Post>> {
//            override fun onChanged(t: List<Post>?) {
//                adapter.setPosts(t)
//            }
//        })

        fab.setOnClickListener { view ->
            Toast.makeText(this, "New Activity", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, NewEventActivity::class.java)
            startActivityForResult(intent, NEW_POST_ACTIVITY_CODE)
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_logout -> {
                firebaseAuth.signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_settings -> {
                Toast.makeText(this, "Settings - Not Implemented Yet", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, SettingsActivity::class.java)
//                startActivity(intent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == NEW_POST_ACTIVITY_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                if (data!!.hasExtra(EXTRA_POST_REPLY)) {
                    var post = data.extras!!.getParcelable<Post>(EXTRA_POST_REPLY)
                    postViewModel.insert(post)
                    databaseReference.child(post.firebase_id).setValue(post)
                    Toast.makeText(this, R.string.event_added, Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, R.string.event_add_failed, Toast.LENGTH_LONG).show()
            }
        } else if(requestCode == UPDATE_POST_CODE) {
            if(!this.activeInternetConnection(this)) {
                Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show()
            } else {
                if (resultCode == Activity.RESULT_OK) {
                    if (data!!.hasExtra(EXTRA_POST_REPLY)) {
                        var post = data.extras!!.getParcelable<Post>(EXTRA_POST_REPLY)
                        postViewModel.updateOne(post)
                        databaseReference.child(post.firebase_id).setValue(post)
                        Toast.makeText(this, R.string.event_updated, Toast.LENGTH_LONG).show()
                    } else if (data!!.hasExtra(DELETE_POST_REPLY)) {
                        var post = data.extras!!.getParcelable<Post>(DELETE_POST_REPLY)
                        postViewModel.deleteOne(post)
                        databaseReference.child(post.firebase_id).removeValue()
                        Toast.makeText(this, R.string.event_deleted, Toast.LENGTH_LONG).show()
                    } else if (data!!.hasExtra(EXTRA_OPEN_REPLY)) {
                        Toast.makeText(this, R.string.event_saved, Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, R.string.event_update_failed, Toast.LENGTH_LONG).show()
                }
            }
        }
        else {
            Toast.makeText(this, R.string.invalid_input, Toast.LENGTH_LONG).show()
        }
    }

    public fun activeInternetConnection(context: Context) : Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true
        return isConnected
    }
}
