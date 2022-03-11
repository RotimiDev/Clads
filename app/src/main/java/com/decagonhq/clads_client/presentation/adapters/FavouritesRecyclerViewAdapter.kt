package com.decagonhq.clads_client.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagonhq.clads_client.data.model.Tailor
import com.decagonhq.clads_client.databinding.FavouritesItemBinding

class FavouritesRecyclerViewAdapter() :
    RecyclerView.Adapter<FavouritesRecyclerViewAdapter.FavouritesViewHolder>() {

    inner class FavouritesViewHolder(binding: FavouritesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var favouriteItemTextView = binding.favouriteRecyclerItemTextview
        private var favouriteItemImageView = binding.favouriteRecyclerItemImageview

        fun bindData(favourite:Tailor) = with(itemView) {
            Glide.with(context).load(favourite.image).into(favouriteItemImageView)
            favouriteItemTextView.text = favourite.name
        }

    }

    private val differCallBack = object : DiffUtil.ItemCallback<Tailor>(){
        override fun areItemsTheSame(oldItem: Tailor, newItem: Tailor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Tailor, newItem: Tailor): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val binding = FavouritesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
