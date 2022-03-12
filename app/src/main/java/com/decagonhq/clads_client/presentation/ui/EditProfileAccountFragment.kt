package com.decagonhq.clads_client.presentation.ui

import android.app.Dialog
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentEditProfileAccountBinding
import com.decagonhq.clads_client.presentation.model.DeliveryAddresse
import com.decagonhq.clads_client.presentation.model.UpdateProfileRequest
import com.decagonhq.clads_client.presentation.viewModel.UpdateProfileViewModel
import com.decagonhq.clads_client.presentation.viewModel.EditProfileViewModel
import com.google.android.material.snackbar.Snackbar
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.decagonhq.clads_client.presentation.viewmodel.DashboardViewModel
import com.decagonhq.clads_client.utils.Resource
import com.decagonhq.clads_client.utils.SessionManager
import com.decagonhq.clads_client.utils.SessionManager.TOKEN
import com.decagonhq.clads_client.utils.viewextensions.provideCustomAlertDialog
import com.decagonhq.clads_client.utils.viewextensions.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MultipartBody

@AndroidEntryPoint
class EditProfileAccountFragment : Fragment() {

    private val viewModel: EditProfileViewModel by activityViewModels()
    private lateinit var profileViewModel: DashboardViewModel
    private var _binding: FragmentEditProfileAccountBinding? = null
    private val binding get() = _binding!!
    private val updateViewModel: UpdateProfileViewModel by activityViewModels ()
    private lateinit var dialog :Dialog
    private val requestWriteStorage = 0
    private var selectedImageUri: Uri? = null

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
        profileViewModel = (activity as DashboardActivity).viewModel

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

        binding.accountImageView.setOnClickListener { requestWritePermission() }

        viewModel.postImage.observe(viewLifecycleOwner, Observer{
            when (it) {
                is Resource.Success -> {
                    Glide.with(requireContext()).load(it.data?.payload?.downloadUri).into(binding.accountImageView)
                }
                is Resource.Loading -> {
                    requireView().showSnackBar(getString(R.string.loading))
                }
                is Resource.Error -> {
                    requireView().showSnackBar(it.data?.message.toString())
                }
            }
        })

        profileViewModel.dashboardProfileDetails.observe(viewLifecycleOwner){ profile ->

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

    // This is my Image Picker
    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_PICK_IMAGE -> {
                    selectedImageUri = data?.data
                    val stringUri = MultipartBody.Part.createFormData(
                        "image",
                        selectedImageUri.toString()
                    )
                    viewModel.uploadImage("", stringUri)
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }

    // My Permission Blocks for Storage access
    private fun requestWritePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                requestWriteStorage
            )
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                requestWriteStorage
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        if (requestCode == requestWriteStorage) {
            // Request for Storage permission.
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // my code
                openImageChooser()
            } else {
                // Permission request was denied for now
            }
        }
    }
}
