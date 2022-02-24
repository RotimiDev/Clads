package com.decagonhq.clads_client.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagonhq.clads_client.databinding.FavouritesItemBinding
import com.decagonhq.clads_client.presentation.model.FavouritesItem


class FavouritesRecyclerViewAdapter(private val message: ArrayList<FavouritesItem>) :
    RecyclerView.Adapter<FavouritesRecyclerViewAdapter.FavouritesViewHolder>() {

    inner class FavouritesViewHolder(binding: FavouritesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var favouriteItemTextView = binding.favouriteRecyclerItemTextview
        private var favouriteItemImageView = binding.favouriteRecyclerItemImageview

        fun bindData(favourite: FavouritesItem) = with(itemView) {
            Glide.with(context).load(favourite.image).into(favouriteItemImageView)
            favouriteItemTextView.text = favourite.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val binding = FavouritesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        holder.bindData(message[position])
    }

    override fun getItemCount(): Int {
        return message.size
    }
}