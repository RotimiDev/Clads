package com.decagonhq.clads_client.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.presentation.utils.validateField
import com.decagonhq.clads_client.databinding.FragmentLoginFormBinding
import com.decagonhq.clads_client.presentation.model.LoginRequest
import com.decagonhq.clads_client.presentation.utils.*
import com.decagonhq.clads_client.presentation.viewModel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFormFragment : Fragment() {
    private var _binding: FragmentLoginFormBinding? = null
    private val binding get() = _binding!!
    private lateinit var email: EditText
    private lateinit var password: EditText
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) { email = enterEmailEditText
            password = passwordEditText
        }

        setUpObservers()
        validateFields()
    }


    //viewModel Observer
    private fun setUpObservers() {
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val intent = Intent(requireContext(), DashboardActivity::class.java)
                    startActivity(intent)
                }
                is Resource.Error -> {
                    Snackbar.make(
                        requireView(),it.message!!,
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




    private fun validateFields() {
        binding.apply {
            val fieldTypesToValidate = listOf(FieldValidationTracker.FieldType.EMAIL)
            FieldValidationTracker.populateFieldTypeMap(fieldTypesToValidate)

            emailAddressLayout.validateField(
                getString(R.string.enter_valid_email_str),
                FieldValidationTracker.FieldType.EMAIL
            ) { input ->
                RegistrationUtil.verifyEmail(input)
            }

            FieldValidationTracker.isFieldsValidated.observe(viewLifecycleOwner) {
                loginButton.apply {
                    //Log.d("TAG_SIGN", "validateFields: $it")
                    isEnabled = !it.values.contains(false)
                    backgroundTintList = if (!it.values.contains(false))
                        ContextCompat.getColorStateList(requireContext(), R.color.white) else
                        ContextCompat.getColorStateList(requireContext(), R.color.grey)
                }
            }

            loginButton.setOnClickListener {

                if(!RegistrationUtil.validateLoginPassword(password.text.toString())){
                    Snackbar.make(
                    requireView(), getString(R.string.wrong_password),
                    Snackbar.LENGTH_LONG
                ).show() }
                else {
                    viewModel.loginUser(LoginRequest(email.text.toString(),
                        password.text.toString()))
                    }

                }
            }
        }
    }


