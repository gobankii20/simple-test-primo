package com.android.post.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.post.R
import com.android.post.databinding.ActivityPostsBinding


class PostsActivity : AppCompatActivity() {

    private var activityPostsBinding: ActivityPostsBinding? = null
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPostsBinding = DataBindingUtil.setContentView(this, R.layout.activity_posts)

        setUpNav()
    }

    private fun setUpNav() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    companion object {
        private val TAG = PostsActivity::class.java.name
    }
}
