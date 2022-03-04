package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentResetEmailTemplateBinding

class ResetEmailTemplateFragment : Fragment(R.layout.fragment_reset_email_template) {
    private var _binding: FragmentResetEmailTemplateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResetEmailTemplateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resetPasswordButton.setOnClickListener {
            val resetEmailDirections = ResetEmailTemplateFragmentDirections.actionResetEmailTemplateFragmentToNewPasswordFragment()
            findNavController().navigate(resetEmailDirections)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
