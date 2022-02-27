package com.decagonhq.clads_client.presentation.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.data.model.PhotoGalleryModel
import com.decagonhq.clads_client.databinding.FragmentMediaBinding
import com.decagonhq.clads_client.presentation.ui.adapters.PhotoGalleryAdapter
import com.decagonhq.clads_client.presentation.viewModel.MediaGalleryViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaFragment : Fragment(), PhotoGalleryAdapter.OnItemClickListener {
    private val viewModel: MediaGalleryViewModel by activityViewModels()
    private val requestWriteStorage = 0
    private lateinit var recyclerView: RecyclerView
    private var selectedImageUri: Uri? = null
    private lateinit var fab: FloatingActionButton
    private var _binding: FragmentMediaBinding? = null
    private val binding get() = _binding!!
    private lateinit var galleryAdapter: PhotoGalleryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()

        // Observing my recyclerView from viewModel, and updating accordingly
        viewModel.photoGallery.observe(viewLifecycleOwner) {
            galleryAdapter.submitList(it)
            if (galleryAdapter.itemCount > 0) {
                binding.mediaFragmentEmptyAvatar.isVisible = false
                binding.mediaFragmentEmptyAvatarTextView.isVisible = false
            }
        }

        recyclerView.adapter = galleryAdapter

        fab.setOnClickListener {
            requestWritePermission()
        }
    }

    // Initialize all my views
    private fun initViews() {
        with(binding) {
            fab = mediaFragmentFab
            recyclerView = mediaFragmentRecyclerView
            galleryAdapter = PhotoGalleryAdapter(this@MediaFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // This is my Image Picker
    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_PICK_IMAGE -> {
                    selectedImageUri = data?.data

                    val stringUri = selectedImageUri.toString()
                    val bundle = Bundle()
                    bundle.putString("Image", stringUri)
                    MediaCaptionFragment().arguments = bundle
                    findNavController().navigate(R.id.mediaCaptionFragment, bundle)
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }

// My Permission Blocks for Storage access
    private fun requestWritePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                requestWriteStorage
            )
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                requestWriteStorage
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        if (requestCode == requestWriteStorage) {
            // Request for Storage permission.
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // my code
                openImageChooser()
            } else {
                // Permission request was denied for now
            }
        }
    }


// Add contact details to bundle and navigates to targeted fragment
    override fun onClick(gallery: PhotoGalleryModel) {
        val mediaBundle = Bundle()
        mediaBundle.putString("key", gallery.imageUrl)
        findNavController().navigate(R.id.mediaDetailsFragment, mediaBundle)
    }
}
