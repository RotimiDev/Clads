package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.ActivityDashboardBinding
import com.decagonhq.clads_client.presentation.utils.Resource
import com.decagonhq.clads_client.presentation.utils.validation.SessionManager
import com.decagonhq.clads_client.presentation.utils.validation.SessionManager.FIRST_NAME
import com.decagonhq.clads_client.presentation.utils.validation.SessionManager.LAST_NAME
import com.decagonhq.clads_client.presentation.utils.viewextensions.showSnackBar
import com.decagonhq.clads_client.presentation.viewmodel.DashboardViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
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
    private lateinit var name: TextView
    private lateinit var fullName:String
    val viewModel : DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val token = SessionManager.readFromSharedPref(this, SessionManager.TOKEN)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()
        // Initialized views with binding
        bottomNavigationView = binding.bottomNavigationView
        navView = binding.mainActivityNavView
        mDrawer = binding.drawerLayout
        val navViewHeader = navView.getHeaderView(0)
        editProfileButton = navViewHeader.findViewById(R.id.nav_drawer_editProfile_button)

        setSupportActionBar(binding.toolbarInclude.toolbar)

        viewModel.getDetails("Bearer $token")
        viewModel.dashboardProfileDetails.observe(this, { profile ->

            when (profile) {

                is Resource.Success -> {
                    name = navViewHeader.findViewById(R.id.nav_drawer_editProfile_name_textView)
                    fullName= profile.data?.payload?.firstName +" "+ profile.data?.payload?.lastName
                    name.text = fullName
                }
                is Resource.Error -> {
                    Toast.makeText(this,"Error:" + profile.data?.message,Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> { name.text= R.string.loading.toString()}
            }
        })

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
                R.id.logout -> {
                    mAuth.signOut()
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
