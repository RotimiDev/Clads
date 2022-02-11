package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.decagonhq.clads_client.R

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        setTheme(R.style.Theme_CladsClient)
    }
}
