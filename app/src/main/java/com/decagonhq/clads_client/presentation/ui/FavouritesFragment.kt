package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.decagonhq.clads_client.data.local.FavouritesDatabase
import com.decagonhq.clads_client.databinding.FragmentFavouritesBinding
import com.decagonhq.clads_client.presentation.adapters.FavouritesRecyclerViewAdapter
import com.decagonhq.clads_client.data.model.FavouritesDataSource
import com.decagonhq.clads_client.data.repository.FavouritesRepository

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = FavouritesDataSource.createDataSet()

        binding.apply {
            favouriteRecyclerview.adapter = FavouritesRecyclerViewAdapter(message)
        }
        if (message.isEmpty()) {
            binding.favouriteLoveIcon.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
