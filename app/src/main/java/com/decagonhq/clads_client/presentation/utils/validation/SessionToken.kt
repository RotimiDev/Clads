package com.decagonhq.clads_client.presentation.utils.validation

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.decagonhq.clads_client.R

object SessionToken {

    lateinit var cladsSharedPreferences: SharedPreferences

    fun createSharedPref(context: Context) {
        cladsSharedPreferences = context.getSharedPreferences(R.string.app_name.toString(), MODE_PRIVATE)
    }

    fun editSharedPref(token: String) {
        with(cladsSharedPreferences.edit()) {
            putString(R.string.app_name.toString(), token)
                .apply()
        }
    }

    fun clearSharedPref() {
        cladsSharedPreferences.edit().clear().apply()
    }
}
