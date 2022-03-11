package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.decagonhq.clads_client.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_sign_up_options)
    }
}
