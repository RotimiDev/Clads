package com.decagonhq.clads_client.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentWelcomeBinding
import com.decagonhq.clads_client.presentation.utils.Resource
import com.decagonhq.clads_client.presentation.utils.validation.SessionManager
import com.decagonhq.clads_client.presentation.utils.validation.SessionManager.TOKEN
import com.decagonhq.clads_client.presentation.utils.viewextensions.showSnackBar
import com.decagonhq.clads_client.presentation.viewmodel.WelcomeViewModel

class WelcomeFragment : Fragment() {

    private lateinit var token: String
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WelcomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get token from fragment arguments and make the verification call
        token = arguments?.getString("token").toString()
        viewModel.verifyAuthToken(token)
        SessionManager.saveToSharedPref(requireContext(), TOKEN, token)

        //observe the status of the verification
        viewModel.authenticationToken.observe(viewLifecycleOwner, { status ->

            when (status) {
                is Resource.Success -> {
                    requireView().showSnackBar(R.string.email_verified)
                    val intent = Intent(requireContext(), DashboardActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                is Resource.Error -> {
                    requireView().showSnackBar("Error:" + status.message.toString())
                }
                is Resource.Loading -> {
                    requireView().showSnackBar(getString(R.string.loading))
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
