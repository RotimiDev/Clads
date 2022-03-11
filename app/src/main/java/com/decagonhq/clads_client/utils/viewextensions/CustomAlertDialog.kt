package com.decagonhq.clads_client.utils.viewextensions

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads_client.R

fun Fragment.provideCustomAlertDialog(): Dialog {

    val dialog by lazy {
        Dialog(requireContext()).apply {
            setContentView(R.layout.custom_loading_dialog)
            setCanceledOnTouchOutside(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }
    return dialog
}
