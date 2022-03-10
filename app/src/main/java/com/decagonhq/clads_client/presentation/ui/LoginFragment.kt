package com.decagonhq.clads_client.presentation.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentLoginFormBinding
import com.decagonhq.clads_client.presentation.model.LoginRequest
import com.decagonhq.clads_client.presentation.utils.Resource
import com.decagonhq.clads_client.presentation.utils.validation.FieldValidationTracker
import com.decagonhq.clads_client.presentation.utils.validation.FieldValidations
import com.decagonhq.clads_client.presentation.utils.validation.observeFieldsValidationToEnableButton
import com.decagonhq.clads_client.presentation.utils.validation.validateField
import com.decagonhq.clads_client.presentation.utils.viewextensions.provideCustomAlertDialog
import com.decagonhq.clads_client.presentation.utils.viewextensions.showSnackBar
import com.decagonhq.clads_client.presentation.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginFormBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialog: Dialog
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            dialog = provideCustomAlertDialog()
            setUpObservers()
            validateFields()

            loginButton.setOnClickListener {
                viewModel.loginUser(
                    LoginRequest(
                        enterEmailEditText.text.toString(),
                        passwordEditText.text.toString()
                    )
                )
            }
        }
    }

    // viewModel Observer
    private fun setUpObservers() {
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    dialog.dismiss()
                    val intent = Intent(requireContext(), DashboardActivity::class.java)
                    startActivity(intent)
                }
                is Resource.Error -> {
                    dialog.dismiss()
                    requireView().showSnackBar(it.message!!)
                }
                is Resource.Loading -> {
                    dialog.show()
                }
            }
        }
    }

    private fun validateFields() {
        binding.apply {
            val fieldTypesToValidate =
                listOf(FieldValidationTracker.FieldType.EMAIL)
            FieldValidationTracker.populateFieldTypeMap(fieldTypesToValidate)

            emailAddressLayout.validateField(
                getString(R.string.enter_valid_email_str),
                FieldValidationTracker.FieldType.EMAIL
            ) { input ->
                FieldValidations.verifyEmail(input)
            }

            loginButton.observeFieldsValidationToEnableButton(
                requireContext(),
                viewLifecycleOwner
            )
        }
    }
}
