package com.decagonhq.clads_client.presentation.ui

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.decagonhq.clads_client.R

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            window.navigationBarColor = resources.getColor(R.color.deep_sky_blue, this.theme)
        }
    }
}
