package com.salesforce.mivi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.salesforce.mivi.R
import com.salesforce.mivi.databinding.ActivityNavigationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {

    private lateinit var activityNavigationBinding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNavigationBinding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(activityNavigationBinding.root)
        val navController = findNavController(R.id.content_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.search_dest, R.id.favorite_dest
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        activityNavigationBinding.bottomNavigation.setupWithNavController(navController)
    }
}