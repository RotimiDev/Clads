package com.decagonhq.clads_client.presentation.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.data.model.Tailor
import com.decagonhq.clads_client.databinding.FragmentArtisanProfileBinding
import com.decagonhq.clads_client.presentation.utils.viewextensions.showSnackBar
import com.decagonhq.clads_client.presentation.viewmodel.ProfileFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtisanProfileFragment : Fragment() {
    private var _binding: FragmentArtisanProfileBinding? = null
    private val binding get() = _binding!!
    private val args: ArtisanProfileFragmentArgs by navArgs()
    private lateinit var tailor:Tailor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArtisanProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tailor= args.tailor
        binding.artisanAddressTextView.text = tailor.location
        binding.artisanNameTextView.text = tailor.name
        Glide.with(requireContext()).load(tailor.image).into(binding.shapeAbleImageView)

        binding.goToGalleryButton.setOnClickListener {findNavController().navigate(R.id.mediaFragment)}
        binding.callButton.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + tailor.phone)
            startActivity(dialIntent)
        }

    }
}
