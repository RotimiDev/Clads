package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.ActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    private lateinit var mDrawer: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navView: NavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var editProfileButton: MaterialButton
    private lateinit var favouriteText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialized views with binding
        bottomNavigationView = binding.bottomNavigationView
        navView = binding.mainActivityNavView
        mDrawer = binding.drawerLayout
        val navViewHeader = navView.getHeaderView(0)
        editProfileButton = navViewHeader.findViewById(R.id.nav_drawer_editProfile_button)
        setSupportActionBar(binding.toolbarInclude.toolbar)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)

        // Setup drawer layout
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.mainActivityNavView

        appBarConfiguration = AppBarConfiguration(
            navHostFragment.navController.graph, drawerLayout
        )

        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)

        navHostFragment.navController.let { navView.setupWithNavController(it) }

        editProfileButton.setOnClickListener {
            navHostFragment.navController.navigate(R.id.editProfileFragment)
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        binding.mainActivityNavView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favouritesFragment -> {
                    Toast.makeText(this, "CLICKED", Toast.LENGTH_SHORT).show()
                    findNavController(R.id.fragmentContainerView).navigate(R.id.favouritesFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                else -> return@setNavigationItemSelectedListener true
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navHostFragment.navController
            .navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
