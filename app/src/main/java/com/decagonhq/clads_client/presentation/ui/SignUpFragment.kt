package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentSignUpBinding
import com.decagonhq.clads_client.presentation.utils.FieldValidationTracker
import com.decagonhq.clads_client.presentation.utils.FieldValidationTracker.FieldType
import com.decagonhq.clads_client.presentation.utils.RegistrationUtil.verifyEmail
import com.decagonhq.clads_client.presentation.utils.RegistrationUtil.verifyName
import com.decagonhq.clads_client.presentation.utils.RegistrationUtil.verifyPassword
import com.decagonhq.clads_client.presentation.utils.validateConfirmPassword
import com.decagonhq.clads_client.presentation.utils.validateField

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

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

            FieldValidationTracker.isFieldsValidated.observe(viewLifecycleOwner, {
                signUpSubmitButton.apply {
                    isEnabled = !it.values.contains(false)
                    backgroundTintList = if (!it.values.contains(false))
                        ContextCompat.getColorStateList(requireContext(), R.color.white) else
                        ContextCompat.getColorStateList(requireContext(), R.color.grey)
                }
            })

            signUpSubmitButton.setOnClickListener {
                val signUpDirections =
                    SignUpFragmentDirections.actionSignUpFragmentToEmailConfirmationFragment()
                findNavController().navigate(signUpDirections)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
