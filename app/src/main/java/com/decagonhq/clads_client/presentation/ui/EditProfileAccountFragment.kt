package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentEditProfileAccountBinding
import com.decagonhq.clads_client.presentation.model.UpdateProfileRequest
import com.decagonhq.clads_client.presentation.utils.Resource
import com.decagonhq.clads_client.presentation.utils.validation.SessionManager
import com.decagonhq.clads_client.presentation.utils.validation.SessionManager.TOKEN
import com.decagonhq.clads_client.presentation.viewModel.UpdateProfileViewModel
import com.decagonhq.clads_client.presentation.viewmodel.EditProfileViewModel
import com.google.android.material.snackbar.Snackbar

class EditProfileAccountFragment : Fragment() {
    private val viewModel: EditProfileViewModel by activityViewModels()
    private var _binding: FragmentEditProfileAccountBinding? = null
    private val binding get() = _binding!!
    private val updateViewModel: UpdateProfileViewModel by viewModels ()

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

            accountSaveChangeButton.setOnClickListener {
                updateViewModel.updateUserProfile(
                    UpdateProfileRequest(
                        accountFirstNameTextView.text.toString(),
                        accountLastNameTextView.text.toString(),
                        accountOtherNameTextView.text.toString(),
                        accountGenderTextView.text.toString(),
                        accountDeliveryAddressTextView.text.toString(),
                    )
                )
            }
        }

        val token = SessionManager.readFromSharedPref(requireContext(), TOKEN)

        viewModel.getProfileDetails("Bearer $token")

        viewModel.profileDetails.observe(viewLifecycleOwner) { profile ->

            when (profile) {

                is Resource.Success -> {
                    binding.accountCityEditText.setText(profile.data?.payload?.showroomAddress?.city)
                    binding.accountStreetEditText.setText(profile.data?.payload?.showroomAddress?.state)
                    binding.editLastNameEditText.setText(profile.data?.payload?.lastName)
                    binding.editFirstNameEditText.setText(profile.data?.payload?.firstName)
                    if (profile.data?.payload?.gender === R.string.male.toString()) {
                        binding.profileGenderRadioGroup.check(R.id.profile_male_radio_button)
                    }
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
        }
    }
}
