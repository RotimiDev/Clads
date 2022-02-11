package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentResetEmailTemplateBinding

class ResetEmailTemplateFragment : Fragment(R.layout.fragment_reset_email_template) {
    private lateinit var binding: FragmentResetEmailTemplateBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResetEmailTemplateBinding.bind(view)

        binding.resetPasswordButton.setOnClickListener {
            val resetEmailDirections = ResetEmailTemplateFragmentDirections.actionResetEmailTemplateFragmentToNewPasswordFragment()
            findNavController().navigate(resetEmailDirections)
        }
    }
}
