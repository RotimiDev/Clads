package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads_client.databinding.FragmentEditProfileSecurityBinding

class EditProfileSecurityFragment : Fragment() {
    private var _binding: FragmentEditProfileSecurityBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditProfileSecurityBinding.inflate(inflater, container, false)
        return binding.root
    }
}
