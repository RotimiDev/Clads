package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentEditProfileAccountBinding
import com.decagonhq.clads_client.presentation.utils.Resource
import com.decagonhq.clads_client.presentation.utils.validation.SessionToken
import com.decagonhq.clads_client.presentation.viewModel.EditProfileViewModel
import com.google.android.material.snackbar.Snackbar

class EditProfileAccountFragment : Fragment() {
    private val viewModel: EditProfileViewModel by activityViewModels()
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

        val token: String = SessionToken.cladsSharedPreferences.getString(R.string.app_name.toString(), "").toString()
        Log.d("TOKEN",token)
        viewModel.getProfileDetails("Bearer $token")
        viewModel.profileDetails.observe(viewLifecycleOwner, { profile ->

            when (profile) {

                is Resource.Success -> {
                    binding.accountCityEditText.text = profile.data?.payload?.showroomAddress?.city
                    binding.accountStreetEditText.text = profile.data?.payload?.showroomAddress?.state
                    binding.accountLastNameTextView.text = profile.data?.payload?.lastName
                    binding.editFirstNameEditText.text = profile.data?.payload?.firstName
                }
                is Resource.Error -> {
                    Snackbar.make(
                        requireView(), "Error:" + profile.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is Resource.Loading -> {
                    Snackbar.make(
                        requireView(), getString(R.string.loading),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        })
    }
}
