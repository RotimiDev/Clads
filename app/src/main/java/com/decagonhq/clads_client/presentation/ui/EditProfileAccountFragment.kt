package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentEditProfileAccountBinding

class EditProfileAccountFragment : Fragment() {
    private var _binding: FragmentEditProfileAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditProfileAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val states = requireContext().resources.getStringArray(R.array.State)
            val arrayAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, states)
            (accountStateTextInput.editText as? AutoCompleteTextView)?.setAdapter(arrayAdapter)
        }
    }
}
