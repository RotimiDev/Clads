package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentSignUpBinding
import com.decagonhq.clads_client.presentation.ui.utils.RegistrationUtil

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

        val sampleFirstName = _binding?.firstNameTextView
        val sampleOtherName = _binding?.OtherNameTextView
        val sampleLastName = _binding?.lastNameTextView
        val sampleEmailAddress = _binding?.emailAddressTextview
        val sampleAccountCategory = _binding?.spinner?.selectedItem
        val samplePassword = _binding?.passwordTextview
        val sampleConfirmPassword = _binding?.confirmPasswordTextview

        // Verify the first name provided by the user.
        fun verifyFirstName(): Boolean {
            return if (RegistrationUtil.verifyName(sampleFirstName?.text.toString().trim())){
                true
            } else {
                sampleFirstName?.error = "Please enter a valid name"
                false
            }
        }

        // Verify the other name provided by the user.
        fun verifyOtherName(): Boolean {
            return if (RegistrationUtil.verifyName(sampleOtherName?.text.toString().trim())){
                true
            } else {
                sampleOtherName?.error = "Please enter a valid name"
                false
            }
        }

        // Verify the last name provided by the user.
        fun verifyLastName(): Boolean {
            return if (RegistrationUtil.verifyName(sampleLastName?.text.toString().trim())){
                true
            } else {
                sampleLastName?.error = "Please enter a valid name"
                false
            }
        }

        // Verify the type of the account selected by the user
        fun verifyAccountCategory(): Boolean {
            return RegistrationUtil.verifyAccountCategory(sampleAccountCategory.toString())
        }

        // Verify the email provided by the user.
        fun verifyEmail(): Boolean {
            return if (RegistrationUtil.verifyEmail(sampleEmailAddress?.text.toString().trim())){
                sampleEmailAddress?.error = null
                true
            } else {
                sampleEmailAddress?.error = "Please enter a valid email"
                false
            }
        }

        // Verify the initial password provided by the user.
        fun verifyPassword(): Boolean {
            return if (RegistrationUtil.verifyPassword(samplePassword?.text.toString().trim())){
                samplePassword?.error = null
                true
            } else {
                samplePassword?.error = "Password must have at least one special character, " +
                        "no white spaces and at least 4 characters."
                false
            }
        }

        // Verify the second confirmation
        fun verifyConfirmPassword(): Boolean {
            return if (RegistrationUtil.verifyPassword(sampleConfirmPassword?.text.toString().trim())
                && (sampleConfirmPassword?.text.toString().trim() == samplePassword?.text.toString().trim())){
                _binding?.ConfirmPasswordLayout?.error = null
                _binding?.ConfirmPasswordLayout?.isErrorEnabled = false
                true
            } else {
                _binding?.ConfirmPasswordLayout?.error = "Password does not match."
                false
            }
        }

        // Registers user, when the verification methods are all valid.
        fun registerUser (){
            if (verifyFirstName() && verifyLastName() && verifyOtherName()
                && verifyEmail() && verifyPassword() && verifyConfirmPassword()){
                Toast.makeText(context, "Account successfully created!",Toast.LENGTH_SHORT).show()

                // Navigates to email confirmation page, if registration is successful.
                val signUpDirections = SignUpFragmentDirections.actionSignUpFragmentToEmailConfirmationFragment()
                findNavController().navigate(signUpDirections)
            }
        }

        // On click of this button it initiates the validation checks and send registers the user if all the validation
        binding.SignupButton.setOnClickListener {
            verifyFirstName()
            verifyOtherName()
            verifyLastName()
            verifyAccountCategory()
            verifyEmail()
            verifyPassword()
            verifyConfirmPassword()

            registerUser()

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
