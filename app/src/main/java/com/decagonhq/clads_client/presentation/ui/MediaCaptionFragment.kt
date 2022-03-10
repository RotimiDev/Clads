package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.data.model.PhotoGalleryModel
import com.decagonhq.clads_client.databinding.FragmentMediaMiddleBinding
import com.decagonhq.clads_client.presentation.utils.viewextensions.showSnackBar
import com.decagonhq.clads_client.presentation.viewModel.MediaGalleryViewModel
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaCaptionFragment : Fragment() {
    private val viewModel: MediaGalleryViewModel by activityViewModels()
    private var _binding: FragmentMediaMiddleBinding? = null
    private val binding get() = _binding!!
    private lateinit var image: ImageView
    private lateinit var captionButton: MaterialButton
    private lateinit var captionEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMediaMiddleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val file = requireArguments().getString("Image")

        initViews()
        image.setImageURI(file?.toUri())

        val editTextMessage = captionEditText.text

        // Add data to viewModel list when click the button
        captionButton.setOnClickListener {
            if (editTextMessage.isNotEmpty()) {
                viewModel.addToGallery(PhotoGalleryModel(file, editTextMessage.toString()))
                findNavController().navigate(R.id.mediaFragment)
            } else {
                requireView().showSnackBar(R.string.enter_caption_for_image)
            }
        }
    }

    private fun initViews() {
        with(binding) {
            image = mediaCaptionFragmentImage
            captionButton = mediaCaptionFragmentButton
            captionEditText = mediaCaptionFragmentEditText
        }
    }
}
