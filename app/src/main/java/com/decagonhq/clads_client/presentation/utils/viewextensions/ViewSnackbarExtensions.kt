package com.decagonhq.clads_client.presentation.utils.viewextensions

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.annotation.StringRes
import androidx.core.util.rangeTo
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.presentation.ui.MainActivity
import com.decagonhq.clads_client.presentation.utils.validation.SessionManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

inline fun View.showSnackBar(
    message: String,
    duration: Int = Snackbar.LENGTH_LONG,
    actionText: String,
    crossinline action: () -> Unit
) {
    val snack = Snackbar.make(this, message, duration)
    snack.setAction(actionText) {
        action()
    }
    snack.show()
}

fun View.showSnackBar(
    message: String,
    duration: Int = Snackbar.LENGTH_LONG
) {
    Snackbar.make(this, message, duration).show()
}

fun View.showSnackBar(
    @StringRes message: Int,
    duration: Int = Snackbar.LENGTH_LONG
) {
    Snackbar.make(this, message, duration).show()
}
