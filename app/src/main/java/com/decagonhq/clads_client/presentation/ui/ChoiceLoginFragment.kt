package com.decagonhq.clads_client.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.data.model.Role
import com.decagonhq.clads_client.databinding.FragmentChoiceLoginBinding
import com.decagonhq.clads_client.presentation.viewmodel.GoogleAuthViewModel
import com.decagonhq.clads_client.utils.Resource
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChoiceLoginFragment : Fragment() {
    private var _binding: FragmentChoiceLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GoogleAuthViewModel by viewModels()

    private val googleSignInRequest = registerForActivityResult(
        GoogleSignInActivityContract(),
        ::onGoogleSignInResult
    )
    private val googleSignInOptions: GoogleSignInOptions
        get() = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .requestProfile()
            .build()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChoiceLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            emailSignupButton.setOnClickListener {
                findNavController().navigate(R.id.signUpFragment)
                observers()
            }
            loginTextView.setOnClickListener {
                findNavController().navigate(R.id.loginFormFragment)
            }
            googleSignupButton.setOnClickListener {
                startGoogleSignIn()
            }
        }
    }

    private fun onGoogleSignInResult(result: GoogleSignInActivityContract.Result) = when (result) {
        is GoogleSignInActivityContract.Result.Success -> {
            Snackbar.make(
                requireView(),
                getString(R.string.google_login_successful),
                Snackbar.LENGTH_SHORT
            ).show()
            handleGoogleLogin(result.googleSignInAccount)
        }
        is GoogleSignInActivityContract.Result.Failure -> {
            Snackbar.make(
                requireView(),
                getString(R.string.google_login_failed),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }


    // Trigger function
    private fun startGoogleSignIn() = googleSignInRequest.launch(googleSignInOptions)

    private fun handleGoogleLogin(googleSignInAccount: GoogleSignInAccount) {
        lifecycleScope.launch {
            val googleCredentials = GoogleAuthProvider.getCredential(
                googleSignInAccount.idToken,
                null
            )
            val signInTask = FirebaseAuth.getInstance().signInWithCredential(googleCredentials)

            googleSignInAccount.idToken?.let { viewModel.loginUserWithGoogle(it, Role.CLIENT) }
            when (signInTask) {
                is AuthResult -> { // <- Successfully signed in to firebase with Google Credentials
                    val googleName = googleSignInAccount.displayName ?: "Mr. Smith"
                    val googleEmail = googleSignInAccount.email ?: error("No email available")
                    // Handle account information ...
                }
                else -> {
                }
            }
        }
    }

    private fun observers() {
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val intent = Intent(requireContext(), DashboardActivity::class.java)
                    startActivity(intent)
                }
                is Resource.Error -> {
                    Snackbar.make(
                        requireView(), it.message ?: "",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is Resource.Loading -> {
                    Snackbar.make(requireView(), getString(R.string.loading), Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
