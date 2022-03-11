package com.decagonhq.clads_client.presentation.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentSignUpBinding
import com.decagonhq.clads_client.data.model.RegistrationRequest
import com.decagonhq.clads_client.utils.Resource
import com.decagonhq.clads_client.utils.validation.FieldValidationTracker.FieldType
import com.decagonhq.clads_client.utils.validation.FieldValidationTracker.populateFieldTypeMap
import com.decagonhq.clads_client.utils.validation.FieldValidations.verifyEmail
import com.decagonhq.clads_client.utils.validation.FieldValidations.verifyName
import com.decagonhq.clads_client.utils.validation.FieldValidations.verifyPassword
import com.decagonhq.clads_client.utils.SessionManager
import com.decagonhq.clads_client.utils.SessionManager.FIRST_NAME
import com.decagonhq.clads_client.utils.SessionManager.LAST_NAME
import com.decagonhq.clads_client.utils.validation.observeFieldsValidationToEnableButton
import com.decagonhq.clads_client.utils.validation.validateConfirmPassword
import com.decagonhq.clads_client.utils.validation.validateField
import com.decagonhq.clads_client.utils.viewextensions.provideCustomAlertDialog
import com.decagonhq.clads_client.utils.viewextensions.showSnackBar
import com.decagonhq.clads_client.presentation.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialog: Dialog
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            dialog = provideCustomAlertDialog()

            validateFields()
            setUpObservers()

            loginTextView.setOnClickListener {
                findNavController().navigate(R.id.loginFormFragment)
            }

            signUpSubmitButton.setOnClickListener {
                 val firstName =firstNameTextView.text.toString()
                 val lastName = lastNameTextView.text.toString()

                SessionManager.saveToSharedPref(requireContext(),FIRST_NAME,firstName)
                SessionManager.saveToSharedPref(requireContext(),LAST_NAME,lastName)

                viewModel.registerUser(
                    RegistrationRequest(
                        firstName,
                        lastName,
                        OtherNameTextView.text.toString(),
                        emailAddressTextview.text.toString(),
                        passwordEditText.text.toString(),
                    )
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.firstNameTextView.clearFocus()
    }

    private fun validateFields() {

        val fieldTypesToValidate = listOf(
            FieldType.FIRSTNAME,
            FieldType.LASTNAME,
            FieldType.OTHER_NAME,
            FieldType.EMAIL,
            FieldType.PASSWORD,
            FieldType.CONFIRM_PASSWORD
        )
        populateFieldTypeMap(fieldTypesToValidate)

        binding.apply {
            firstNameLayout.validateField(
                getString(R.string.enter_valid_name_str), FieldType.FIRSTNAME
            ) { input ->
                verifyName(input)
            }
            OtherNameLayout.validateField(
                getString(R.string.enter_valid_name_str), FieldType.OTHER_NAME
            ) { input ->
                verifyName(input)
            }
            lastNameLayout.validateField(
                getString(R.string.enter_valid_name_str), FieldType.LASTNAME
            ) { input ->
                verifyName(input)
            }
            emailAddressLayout.validateField(
                getString(R.string.enter_valid_email_str), FieldType.EMAIL
            ) { input ->
                verifyEmail(input)
            }
            passwordLayout.validateField(
                getString(R.string.enter_valid_password_str),
                FieldType.PASSWORD
            ) { input ->
                verifyPassword(input)
            }

            confirmPasswordLayout.validateConfirmPassword(
                passwordLayout, FieldType.CONFIRM_PASSWORD,
                getString(R.string.enter_valid_confirm_password_str)
            )

            /** This method observes the validation of the fields
             *  disables and enables the button as appropriate
             *  materialButton must be set to default [enable = false]
             *  [backgroundTint = "@color/grey"]
             */
            signUpSubmitButton.observeFieldsValidationToEnableButton(
                requireContext(),
                viewLifecycleOwner
            )
        }
    }
    private fun setUpObservers() {
        viewModel.registerResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    dialog.dismiss()
                    findNavController().navigate(R.id.emailConfirmationFragment)
                }
                is Resource.Error -> {
                    dialog.dismiss()
                    requireView().showSnackBar(R.string.call_failed)
                }
                is Resource.Loading -> {
                    dialog.show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
