package com.example.cashcompassapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            ?.let { it as androidx.navigation.fragment.NavHostFragment }
            ?.navController

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        if (navController != null) {
            bottomNav.setupWithNavController(navController)
        }
    }
}
