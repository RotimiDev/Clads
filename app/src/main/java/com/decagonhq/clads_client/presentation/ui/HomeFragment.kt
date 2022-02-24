package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentHomeBinding
import com.decagonhq.clads_client.presentation.ui.model.TailorDataSource

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var homeFragmentRecyclerAdapter: HomeFragmentRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = TailorDataSource.createDataSet()
        homeFragmentRecyclerAdapter = HomeFragmentRecyclerAdapter(data)
        binding.homeTailorsRecyclerView.adapter = homeFragmentRecyclerAdapter

        binding.homeWeaversRecyclerView.adapter = homeFragmentRecyclerAdapter

        binding.apply {
            val states = requireContext().resources.getStringArray(R.array.State)
            val arrayAdapter =
                ArrayAdapter(requireContext(), R.layout.artisan_autocomplete_text_view, states)
            artisanAutocompleteTextView.setAdapter(arrayAdapter)
            artisanAutocompleteTextView.setDropDownBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.home_search_drawable))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
