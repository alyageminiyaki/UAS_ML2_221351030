package com.example.milk

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.milk.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        val navView: BottomNavigationView = binding.navView
        navView.setupWithNavController(navController)

        setupActionBarWithNavController(navController)

        navView.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                }
                R.id.navigation_profile -> {
                }
            }
        }

        // Mendengarkan perubahan destinasi Fragment untuk mengatur visibilitas UI (AppBar dan BottomNav)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> {
                    navView.visibility = View.VISIBLE
                    supportActionBar?.show()
                }
                R.id.tentangFragment -> {
                    navView.visibility = View.GONE
                    supportActionBar?.show()
                }
                R.id.fiturFragment -> {
                    navView.visibility = View.GONE
                    supportActionBar?.show()
                }
                R.id.arsitekturModelFragment -> {
                    navView.visibility = View.GONE
                    supportActionBar?.show()
                }
                R.id.simulasiFragment -> {
                    navView.visibility = View.GONE
                    supportActionBar?.show()
                }
                R.id.navigation_profile -> {
                    navView.visibility = View.VISIBLE
                    supportActionBar?.show()
                }
                else -> {
                    navView.visibility = View.VISIBLE
                    supportActionBar?.show()
                    supportActionBar?.title = destination.label
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
