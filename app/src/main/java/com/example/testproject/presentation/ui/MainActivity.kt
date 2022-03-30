package com.example.testproject.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.testproject.R
import com.example.testproject.core.App
import com.example.testproject.presentation.util.NetworkManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var networkManager: NetworkManager

    private lateinit var notification: TextView

    private val component by lazy {
        (application as App).component
    }

    private lateinit var bottomNavView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)
        networkManager.registerCallback()

        bottomNavView = findViewById(R.id.bottom_navigation)
        notification = findViewById(R.id.notification)

        setupNavController()

        networkManager.isConnectedNetwork.observe(this) { isNetwork ->
            Log.d("networkManager", "state $isNetwork")
            notification.isVisible = !isNetwork
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        networkManager.unregisterCallback()
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.menuFragment -> showBottomNav()
                R.id.cartFragment -> showBottomNav()
                R.id.profileFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        bottomNavView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        bottomNavView.visibility = View.GONE
    }
}