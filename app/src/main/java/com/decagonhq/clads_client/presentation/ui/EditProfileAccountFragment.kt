package com.decagonhq.clads_client.presentation.ui

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
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentEditProfileAccountBinding
import com.decagonhq.clads_client.presentation.utils.Resource
import com.decagonhq.clads_client.presentation.utils.validation.SessionManager
import com.decagonhq.clads_client.presentation.utils.validation.SessionManager.TOKEN
import com.decagonhq.clads_client.presentation.utils.viewextensions.showSnackBar
import com.decagonhq.clads_client.presentation.viewmodel.EditProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MultipartBody

@AndroidEntryPoint
class EditProfileAccountFragment : Fragment() {
    private val viewModel: EditProfileViewModel by activityViewModels()
    private var _binding: FragmentEditProfileAccountBinding? = null
    private val binding get() = _binding!!
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

        binding.apply {
            val states = requireContext().resources.getStringArray(R.array.State)
            val arrayAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, states)
            (accountStateTextInput.editText as? AutoCompleteTextView)?.setAdapter(arrayAdapter)
        }
        binding.accountImageView.setOnClickListener { requestWritePermission() }

        val token = SessionManager.readFromSharedPref(requireContext(), TOKEN)

        viewModel.getProfileDetails("Bearer $token")

        viewModel.postImage.observe(viewLifecycleOwner, {
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

        viewModel.profileDetails.observe(viewLifecycleOwner, { profile ->

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
                    requireView().showSnackBar("Error:" + profile.data?.message)
                }
                is Resource.Loading -> {
                    requireView().showSnackBar(getString(R.string.loading))
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
