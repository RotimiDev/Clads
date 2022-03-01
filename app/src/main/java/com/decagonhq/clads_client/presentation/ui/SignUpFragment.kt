package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentSignUpBinding
import com.decagonhq.clads_client.presentation.utils.validation.FieldValidationTracker.FieldType
import com.decagonhq.clads_client.presentation.utils.validation.FieldsValidation.verifyEmail
import com.decagonhq.clads_client.presentation.utils.validation.FieldsValidation.verifyName
import com.decagonhq.clads_client.presentation.utils.validation.FieldsValidation.verifyPassword
import com.decagonhq.clads_client.presentation.utils.validation.observeFieldsValidationToEnableButton
import com.decagonhq.clads_client.presentation.utils.validation.validateConfirmPassword
import com.decagonhq.clads_client.presentation.utils.validation.validateField

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var loginTextView: TextView

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

        // Verify the first name provided by the user
        validateFields()
        loginTextView = binding.loginTextView
        loginTextView.setOnClickListener {
            findNavController().navigate(R.id.loginFormFragment)
        }
    }

    private fun validateFields() {
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

            signUpSubmitButton.setOnClickListener {
                findNavController().navigate(R.id.emailConfirmationFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
