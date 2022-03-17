package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagonhq.clads_client.data.model.RatingDataSource
import com.decagonhq.clads_client.databinding.FragmentRatingBinding
import com.decagonhq.clads_client.presentation.adapters.RatingsAdapter

class RatingFragment : Fragment() {
    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!
    private lateinit var ratingsAdapter: RatingsAdapter
    private lateinit var toolbarProfileLayout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarProfileLayout = (activity as DashboardActivity).toolbarProfileLayout
        toolbarProfileLayout.visibility = View.GONE

        val message = RatingDataSource.createDataSet()

        ratingsAdapter = RatingsAdapter(message)
        binding.previousRatingRecyclerView.apply {
            adapter = ratingsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
