package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagonhq.clads_client.databinding.FragmentRatingBinding
import com.decagonhq.clads_client.presentation.adapters.PreviousRatingsRecyclerAdapter

class RatingFragment : Fragment() {
    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!
    lateinit var ratingsAdapter: PreviousRatingsRecyclerAdapter

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

        val message = listOf(
            "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.",
            "Two amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint."
        )

        ratingsAdapter = PreviousRatingsRecyclerAdapter(message)
        binding.previousRatingRecycler.apply {
            adapter = ratingsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
