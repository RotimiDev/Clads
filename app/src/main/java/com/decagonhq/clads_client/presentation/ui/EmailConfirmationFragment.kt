package com.decagonhq.clads_client.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentEmailConfirmationBinding


class EmailConfirmationFragment : Fragment() {
    var _binding : FragmentEmailConfirmationBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,

        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEmailConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.verifyEmailAddressButton.setOnClickListener {
            val intent = Intent(requireContext(), DashboardActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }


}
