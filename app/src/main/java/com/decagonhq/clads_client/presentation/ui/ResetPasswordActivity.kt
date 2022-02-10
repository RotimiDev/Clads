package com.decagonhq.clads_client.presentation.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.decagonhq.clads_client.R

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            window.navigationBarColor = resources.getColor(R.color.white, this.theme)
        }
    }
}