package com.decagonhq.clads_client.presentation.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentEditProfileAccountBinding
import com.decagonhq.clads_client.presentation.model.DeliveryAddresse
import com.decagonhq.clads_client.presentation.model.UpdateProfileRequest
import com.decagonhq.clads_client.presentation.utils.Resource
import com.decagonhq.clads_client.presentation.utils.validation.SessionManager
import com.decagonhq.clads_client.presentation.utils.validation.SessionManager.TOKEN
import com.decagonhq.clads_client.presentation.utils.viewextensions.provideCustomAlertDialog
import com.decagonhq.clads_client.presentation.utils.viewextensions.showSnackBar
import com.decagonhq.clads_client.presentation.viewModel.UpdateProfileViewModel
import com.decagonhq.clads_client.presentation.viewModel.EditProfileViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileAccountFragment : Fragment() {
    private val viewModel: EditProfileViewModel by activityViewModels()
    private var _binding: FragmentEditProfileAccountBinding? = null
    private val binding get() = _binding!!
    private val updateViewModel: UpdateProfileViewModel by viewModels ()
    private lateinit var dialog :Dialog

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
        dialog=provideCustomAlertDialog()

        val token = SessionManager.readFromSharedPref(requireContext(), TOKEN)
        binding.apply {
            val states = requireContext().resources.getStringArray(R.array.State)
            val arrayAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, states)
            (accountStateTextInput.editText as? AutoCompleteTextView)?.setAdapter(arrayAdapter)

            accountSaveChangeButton.setOnClickListener {
                val radioGroup = profileGenderRadioGroup.checkedRadioButtonId
                val radioValue:RadioButton = view.findViewById(radioGroup)
                val state = editProfileStateSpinner.text.toString().trim()
                val city = accountCityEditText.text.toString().trim()
                val street = accountStreetEditText.text.toString().trim()
                updateViewModel.updateUserProfile( "Bearer $token",
                    UpdateProfileRequest (deliveryAddresses = listOf(DeliveryAddresse(city, state, street)),
                        firstName = editFirstNameEditText.text.toString(),
                        lastName = editLastNameEditText.text.toString(),
                        gender = radioValue.text.toString(),
                        email = "xyz@gmail.com"
                    )
                )
            }
        }

        viewModel.getProfileDetails("Bearer $token")

        viewModel.profileDetails.observe(viewLifecycleOwner) { profile ->
            when (profile) {
                is Resource.Success -> {
                    dialog.dismiss()
                    binding.apply{
                        accountCityEditText.setText(profile.data?.payload?.showroomAddress?.city)
                        accountStreetEditText.setText(profile.data?.payload?.showroomAddress?.state)
                        editLastNameEditText.setText(profile.data?.payload?.lastName)
                        editFirstNameEditText.setText(profile.data?.payload?.firstName)
                        if (profile.data?.payload?.gender === R.string.male.toString()) {
                           profileGenderRadioGroup.check(R.id.profile_male_radio_button)
                        }
                    }
                }
                is Resource.Error -> {
                    dialog.dismiss()
                    Snackbar.make(
                        requireView(), "Error:" + profile.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is Resource.Loading -> {
                   dialog.show()
                }
            }
        }

        updateViewModel.updateProfileResponse.observe(viewLifecycleOwner, Observer { updatedProfile->
            when(updatedProfile){
                is Resource.Loading ->{
                    dialog.show()
                }
                is Resource.Error ->{
                    dialog.dismiss()
                    requireView().showSnackBar(getString(R.string.failed_to_update))
                }
                is Resource.Success ->{
                    dialog.dismiss()
                    requireView().showSnackBar(getString(R.string.profile_update_successful))
                }
            }


        })
    }
}
