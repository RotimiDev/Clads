package com.decagonhq.clads_client.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagonhq.clads_client.data.model.PhotoGalleryModel
import com.decagonhq.clads_client.databinding.MediaFragmentRecyclerviewItemLayoutBinding

class PhotoGalleryAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<PhotoGalleryAdapter.PhotoGalleryViewHolder>() {

    private var recyclerViewList = listOf<PhotoGalleryModel>()

    inner class PhotoGalleryViewHolder(
        val binding: MediaFragmentRecyclerviewItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photoGalleryModel: PhotoGalleryModel) {
            with(binding) {
                Glide.with(mediaFragmentRecyclerViewItemLayoutCardViewImage.context)
                    .load(photoGalleryModel.imageUrl)
                    .into(mediaFragmentRecyclerViewItemLayoutCardViewImage)
                mediaFragmentRecyclerViewItemLayoutCardViewTextView.text =
                    photoGalleryModel.description

                mediaFragmentRecyclerViewItemLayoutCardView.setOnClickListener {
                    listener.onClick(photoGalleryModel)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGalleryViewHolder {
        val binding = MediaFragmentRecyclerviewItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return PhotoGalleryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PhotoGalleryViewHolder,
        position: Int
    ) {
        val currentItem = recyclerViewList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return recyclerViewList.size
    }

    fun submitList(gallery: List<PhotoGalleryModel>) {
        val photoGalleryDiffUtils =
            PhotoGalleryDiffUtils(recyclerViewList, gallery)
        val diffUtilResult = DiffUtil.calculateDiff(photoGalleryDiffUtils)
        recyclerViewList = gallery
        diffUtilResult.dispatchUpdatesTo(this)
    }

    interface OnItemClickListener {
        fun onClick(gallery: PhotoGalleryModel)
    }
}
