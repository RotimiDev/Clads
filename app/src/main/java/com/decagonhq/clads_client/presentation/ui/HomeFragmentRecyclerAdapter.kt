package com.decagonhq.clads_client.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagonhq.clads_client.databinding.HomeRecyclerviewItemBinding

class HomeFragmentRecyclerAdapter(
    private val name: List<String>,
    private val locations: List<String>,
    private val image: List<Int>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var locationsTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var imageView: ImageView
    inner class HomeArtisanViewHolder(binding: HomeRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            locationsTextView = binding.homeLocationItemTextView
            nameTextView = binding.homeItemNameTextView
            imageView = binding.homeItemImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = HomeRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeArtisanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            locationsTextView.text = locations[position]
            nameTextView.text = name[position]
            Glide.with(context).load(image[position]).into(imageView)
        }
    }

    override fun getItemCount(): Int {
        return name.size
    }
}
