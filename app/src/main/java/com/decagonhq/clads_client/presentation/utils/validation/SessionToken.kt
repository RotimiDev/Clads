package com.decagonhq.clads_client.presentation.utils.validation

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.decagonhq.clads_client.R


object SessionToken {
    var ctx = getApplicationContext<Context>()
    val sharedPref = ctx.getSharedPreferences(R.string.app_name.toString(),MODE_PRIVATE)
}