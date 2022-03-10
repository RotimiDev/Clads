package com.decagonhq.clads_client.presentation.utils.validation

import android.content.Context
import android.content.Context.MODE_PRIVATE

object SessionManager {

    private const val SHARED_PREF_NAME: String = "Clad_Client_Shared_Pref"
    internal const val TOKEN: String = "TOKEN"

    fun saveToSharedPref(context: Context, key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(key, value).apply()
        }
    }

    fun readFromSharedPref(context: Context, key: String, defaultValue: String = ""): String {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        return sharedPreferences.getString(key, defaultValue).toString()
    }

    fun clearSharedPref(context: Context) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}
