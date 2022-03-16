package com.decagonhq.clads_client.presentation.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.utils.SessionManager
import com.decagonhq.clads_client.utils.SessionManager.TOKEN
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        setTheme(R.style.Theme_CladsClient)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            window.navigationBarColor = resources.getColor(R.color.deep_sky_blue, this.theme)
        }

        lifecycleScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val preference = SessionManager.readFromSharedPref(this@AuthenticationActivity, TOKEN)
                if (preference.isNotBlank()) {
                    val intent =
                        Intent(this@AuthenticationActivity, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
