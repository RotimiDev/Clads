package com.decagonhq.clads_client.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads_client.R
import com.decagonhq.clads_client.databinding.FragmentHomeBinding

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
        val name = listOf("JJ Fashionista Limited", "Lola Jegede Threads", "Amina Yusuf Fashion")
        val locations = listOf("Egbeda, Lagos", "Lekki, Lagos", "Zaria, Kaduna")
        val image = listOf(R.drawable.image_one, R.drawable.image_two, R.drawable.image_three)

        homeFragmentRecyclerAdapter = HomeFragmentRecyclerAdapter(name, locations, image)
        binding.homeTailorsRecyclerView.adapter = homeFragmentRecyclerAdapter

        binding.homeWeaversRecyclerView.adapter = homeFragmentRecyclerAdapter
    }
}
