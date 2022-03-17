package com.decagonhq.clads_client.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.data.model.Tailor
import com.decagonhq.clads_client.databinding.FragmentArtisanProfileBinding
import com.decagonhq.clads_client.presentation.viewmodel.ArtisanProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtisanProfileFragment : Fragment() {

    private val viewModel: ArtisanProfileViewModel by activityViewModels()
    private var _binding: FragmentArtisanProfileBinding? = null
    private val binding get() = _binding!!
    private val args: ArtisanProfileFragmentArgs by navArgs()
    private lateinit var tailor: Tailor
    var likeStatus: Boolean = false
    private lateinit var toolbarProfileLayout: ConstraintLayout

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

        tailor = args.tailor
        toolbarProfileLayout = (activity as DashboardActivity).toolbarProfileLayout
        binding.artisanAddressTextView.text = tailor.location
        toolbarProfileLayout.visibility = View.GONE
        binding.artisanNameTextView.text = tailor.name
        Glide.with(requireContext()).load(tailor.image).into(binding.shapeAbleImageView)

        binding.goToGalleryButton.setOnClickListener { findNavController().navigate(R.id.mediaFragment) }
        binding.seeReviewTextView.setOnClickListener { findNavController().navigate(R.id.ratingFragment) }

        binding.callButton.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + tailor.phone)
            startActivity(dialIntent)
        }

        viewModel.isFavouriteCall(tailor.id).observe(viewLifecycleOwner, { favouriteList ->
            if (favouriteList.isEmpty()) binding.artisanFavoriteCheckBox.isChecked = false
            else {
                binding.artisanFavoriteCheckBox.isChecked = true
                likeStatus = true
            }
            binding.artisanFavoriteCheckBox.setOnClickListener {
                if (favouriteList.isEmpty()) {
                    viewModel.insertFavourite(tailor)
                    binding.artisanFavoriteCheckBox.isChecked = true
                } else {
                    viewModel.deleteFavourite(tailor)
                    binding.artisanFavoriteCheckBox.isChecked = false
                }
            }
        })
    }
}
