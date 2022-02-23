package com.decagonhq.clads_client.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentMediaDetailsBinding

class MediaDetailsFragment : Fragment() {
    private var _binding: FragmentMediaDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mediaDetailsFragmentImage: ImageView
    private lateinit var file: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMediaDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)

        // Initialize imageView and set Image.
        mediaDetailsFragmentImage = binding.mediaDetailsFragmentImageView
        file = requireArguments().getString("key").toString()
        mediaDetailsFragmentImage.setImageURI(file?.toUri())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareImage()
                return true
            }
            R.id.action_edit -> {

                return true
            }
            R.id.action_delete -> {
                dialogue()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

// dialogue for deleting image
    private fun dialogue() {
        val builder = AlertDialog.Builder(requireContext())
        // set title for alert dialog
        builder.setTitle(getString(R.string.delete_image))
        // set message for alert dialog
        builder.setMessage(getString(R.string.message))
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        // performing positive action
        builder.setPositiveButton(requireContext().getString(R.string.yes)) { dialogInterface, which ->
            // deleteContact()
            Toast.makeText(requireContext(), getString(R.string.deleted), Toast.LENGTH_LONG).show()
        }
        // performing negative action
        builder.setNegativeButton(getString(R.string.no)) { dialogInterface, which ->
            Toast.makeText(requireContext(), getString(R.string.abort), Toast.LENGTH_LONG).show()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    // Share image
    private fun shareImage() {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "image/*"
        i.putExtra(Intent.EXTRA_STREAM, file)
        startActivity(Intent.createChooser(i, getString(R.string.share_image)))
    }
}
