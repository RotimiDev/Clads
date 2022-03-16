package com.decagonhq.clads_client.presentation.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.ActivityDashboardBinding
import com.decagonhq.clads_client.presentation.viewmodel.DashboardViewModel
import com.decagonhq.clads_client.utils.Resource
import com.decagonhq.clads_client.utils.SessionManager
import com.decagonhq.clads_client.utils.connectivity.ConnectivityLiveData
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
    private lateinit var name: TextView
    lateinit var toolbarProfileLayout:ConstraintLayout
    private lateinit var toolBarName: TextView
    private lateinit var fullName: String
    private lateinit var connectivityLiveData: ConnectivityLiveData
    val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Calling the function that checks for connectivity and saving it to a variable
        connectivityLiveData = ConnectivityLiveData(this)
        val token = SessionManager.readFromSharedPref(this, SessionManager.TOKEN)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialized views with binding
        bottomNavigationView = binding.bottomNavigationView
        navView = binding.mainActivityNavView
        mDrawer = binding.drawerLayout
        val navViewHeader = navView.getHeaderView(0)
        name = navViewHeader.findViewById(R.id.nav_drawer_editProfile_name_textView)
        toolbarProfileLayout = findViewById(R.id.toolbar_profile_details_layout)
        toolBarName = findViewById(R.id.toolbar_profile_name_text_view)
        editProfileButton = navViewHeader.findViewById(R.id.nav_drawer_editProfile_button)

        setSupportActionBar(binding.toolbarInclude.toolbar)

        //The observer that constantly checks the network state
        connectivityLiveData.observe(this,{ networkState->
            if (networkState == true){
                //Toggle the visibility of the network error layer
                viewModel.getDetails("Bearer $token")
                binding.noNetworkTextView.visibility= View.GONE
            } else binding.noNetworkTextView.visibility= View.VISIBLE
        })

        viewModel.dashboardProfileDetails.observe(this, { profile ->
                when (profile) {
                    is Resource.Success -> {
                        fullName = profile.data?.payload?.firstName + " " + profile.data?.payload?.lastName
                        name.text = fullName
                        toolBarName.text = profile.data?.payload?.firstName
                    }
                    is Resource.Error -> {
                        Toast.makeText(this, "Error:" + profile.data?.message, Toast.LENGTH_LONG).show()
                    }
                    is Resource.Loading -> { name.text = R.string.loading.toString() }
                }
            }
        )

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
                    findNavController(R.id.fragmentContainerView).navigate(R.id.favouritesFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                R.id.logout -> {
                    // Using a dialog to ask the user for confirmation before logging out
                    val confirmationDialog = AlertDialog.Builder(this)
                    confirmationDialog.setMessage(R.string.are_you_sure_you_want_to_log_out)
                    confirmationDialog.setPositiveButton(R.string.yes) { _: DialogInterface, _: Int ->
                        SessionManager.clearSharedPref(this)
                        val intent = Intent(this, AuthenticationActivity::class.java)
                        startActivity(intent)
                    }
                    confirmationDialog.setNegativeButton(
                        R.string.no
                    ) { _: DialogInterface, _: Int ->
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    confirmationDialog.create().show()

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
