package com.decagonhq.clads_client.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentLoginFormBinding
import com.decagonhq.clads_client.presentation.model.LoginRequest
import com.decagonhq.clads_client.presentation.utils.Resource
import com.decagonhq.clads_client.presentation.utils.ValidationLoginUtil
import com.decagonhq.clads_client.presentation.viewModel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFormFragment : Fragment() {
    private var _binding : FragmentLoginFormBinding? = null
    private val binding get() = _binding!!
    private lateinit var email: EditText
    private lateinit var password: TextInputEditText
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginFormBinding.inflate(inflater, container, false)
        return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            email = enterEmailEditText
            password = loginPasswordEditText
        }

        binding.loginButton.setOnClickListener {
            if (ValidationLoginUtil.validateInputs(email.text.toString(),
                    password.text.toString())) {
                viewModel.loginUser(LoginRequest(email.text.toString(), password.text.toString()))

            }else if(!ValidationLoginUtil.validateEmail(email.text.toString())){
                Snackbar.make(requireView(), getString(R.string.wrong_email),
                    Snackbar.LENGTH_LONG).show()

            }else if(!ValidationLoginUtil.validatePassword(password.text.toString())){
                Snackbar.make(requireView(), getString(R.string.wrong_password),
                    Snackbar.LENGTH_LONG).show()

            }
        }

        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    val intent = Intent(requireContext(), DashboardActivity::class.java)
                    startActivity(intent)
                }
                 is Resource.Error -> {
                    Snackbar.make(requireView(), getString(R.string.failed_login),
                        Snackbar.LENGTH_LONG).show()
                 }
                is Resource.Loading -> {
                    Snackbar.make(requireView(), getString(R.string.loading),
                        Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }



}
