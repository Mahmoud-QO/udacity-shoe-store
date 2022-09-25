package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNUSED_VARIABLE")
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        // setup Action Bar with Navigation
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.loginFragment, R.id.welcomeFragment, R.id.shoeListFragment)
        )
        NavigationUI.setupActionBarWithNavController(
            this, findNavController(R.id.fragment_nav_host), appBarConfiguration
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        //return super.onSupportNavigateUp()
        return findNavController(R.id.fragment_nav_host).navigateUp()
    }
}
