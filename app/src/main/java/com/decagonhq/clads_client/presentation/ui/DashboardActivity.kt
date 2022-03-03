package com.decagonhq.clads_client.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.ActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth

    private lateinit var mDrawer: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navView: NavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var editProfileButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()


        // Initialized views with binding
        bottomNavigationView = binding.bottomNavigationView
        navView = binding.mainActivityNavView
        mDrawer = binding.drawerLayout
        editProfileButton = binding.navDrawerEditProfileButton
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

        navHostFragment?.navController.let { navView.setupWithNavController(it) }

        editProfileButton.setOnClickListener {
            navHostFragment.navController.navigate(R.id.editProfileFragment)
        }

    }
    //if the user is not authenticated send him to choiceLoginScreen to authenticate first else send him to dashboard activity
//    override fun onStart() {
//        super.onStart()
//        // Check if user is signed in or not
//        val currentUser = mAuth.currentUser
//        if (currentUser != null) {
//            val intent = Intent(this, DashboardActivity::class.java)
//            startActivity(intent)
//        }else{
//            val intent = Intent(this, ChoiceLoginFragment::class.java)
//            startActivity(intent)
//        }
//
//    }

    override fun onSupportNavigateUp(): Boolean {
        return navHostFragment.navController
            .navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
