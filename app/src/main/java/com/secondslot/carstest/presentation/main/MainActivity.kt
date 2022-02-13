package com.secondslot.carstest.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.secondslot.carstest.R
import com.secondslot.carstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // Initialize navController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController

        // Hook up navigation button (up/drawer) on toolbar
        val appBarConfiguration = AppBarConfiguration(navController.graph, null)

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}
