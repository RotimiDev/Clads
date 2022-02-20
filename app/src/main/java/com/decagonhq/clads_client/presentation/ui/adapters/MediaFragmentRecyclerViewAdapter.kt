package com.decagonhq.clads_client.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagonhq.clads_client.data.model.MediaFragmentModel
import com.decagonhq.clads_client.databinding.MediaFragmentRecyclerviewItemLayoutBinding

class MediaFragmentRecyclerViewAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<MediaFragmentRecyclerViewAdapter.RecyclerViewViewHolder>() {

    private var recyclerViewList = arrayListOf<MediaFragmentModel>()

    inner class RecyclerViewViewHolder(
        val binding: MediaFragmentRecyclerviewItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MediaFragmentRecyclerViewAdapter.RecyclerViewViewHolder {
        val binding = MediaFragmentRecyclerviewItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return RecyclerViewViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MediaFragmentRecyclerViewAdapter.RecyclerViewViewHolder,
        position: Int
    ) {
        with(holder) {
            Glide.with(binding.mediaFragmentRecyclerViewItemLayoutCardViewImage.context)
                .load(recyclerViewList[position].image)
                .into(binding.mediaFragmentRecyclerViewItemLayoutCardViewImage)
            binding.mediaFragmentRecyclerViewItemLayoutCardViewTextView.text = recyclerViewList[position].text

            binding.mediaFragmentRecyclerViewItemLayoutCardView.setOnClickListener {
                listener.onClick(recyclerViewList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return recyclerViewList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(gallery: List<MediaFragmentModel>) {
        recyclerViewList.addAll(gallery)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClick(gallery: MediaFragmentModel)
    }
}
