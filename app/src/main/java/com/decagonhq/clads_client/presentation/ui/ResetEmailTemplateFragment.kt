package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentResetEmailTemplateBinding

class ResetEmailTemplateFragment : Fragment(R.layout.fragment_reset_email_template) {
    private lateinit var binding: FragmentResetEmailTemplateBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResetEmailTemplateBinding.bind(view)

        binding.btnResetPassword.setOnClickListener {
            val resetEmailDirections = ResetEmailTemplateFragmentDirections.actionResetEmailTemplateFragmentToNewPasswordFragment()
            findNavController().navigate(resetEmailDirections)
        }

    }
}