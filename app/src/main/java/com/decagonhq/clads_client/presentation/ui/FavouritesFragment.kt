package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.decagonhq.clads_client.databinding.FragmentFavouritesBinding
import com.decagonhq.clads_client.presentation.adapters.FavouritesRecyclerViewAdapter
import com.decagonhq.clads_client.presentation.viewmodel.FavouritesViewModel

class FavouritesFragment : Fragment() {

    private val viewModel: FavouritesViewModel by activityViewModels()
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private var favouritesRecyclerViewAdapter = FavouritesRecyclerViewAdapter()
    private lateinit var toolbarProfileLayout: ConstraintLayout

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

        toolbarProfileLayout = (activity as DashboardActivity).toolbarProfileLayout
        toolbarProfileLayout.visibility= View.GONE

        viewModel.getAllFavourites().observe(viewLifecycleOwner, {
            favouritesRecyclerViewAdapter.differ.submitList(it)
        })

        binding.apply {
            favouriteRecyclerview.adapter = favouritesRecyclerViewAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
