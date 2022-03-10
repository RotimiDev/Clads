package com.decagonhq.clads_client.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagonhq.clads_client.data.model.Tailor
import com.decagonhq.clads_client.databinding.HomeRecyclerviewItemBinding

class HomeFragmentRecyclerAdapter(
    private var tailorDataSource: List<Tailor>
) : RecyclerView.Adapter<HomeFragmentRecyclerAdapter.HomeArtisanViewHolder>() {

    inner class HomeArtisanViewHolder(binding: HomeRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private var locationsTextView = binding.homeLocationItemTextView
        private var nameTextView = binding.homeItemNameTextView
        private var imageView = binding.homeItemImageView

        fun bind(data: Tailor) = with(itemView) {
            locationsTextView.text = data.location
            nameTextView.text = data.name
            Glide.with(context).load(data.image).into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeArtisanViewHolder {
        val binding = HomeRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeArtisanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeArtisanViewHolder, position: Int) {
        holder.bind(tailorDataSource[position])
    }

    override fun getItemCount(): Int {
        return tailorDataSource.size
    }
}
