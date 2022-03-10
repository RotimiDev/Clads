package com.decagonhq.clads_client.presentation.utils.viewextensions

import android.view.View
import androidx.annotation.StringRes
import androidx.core.util.rangeTo
import com.google.android.material.snackbar.Snackbar

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
