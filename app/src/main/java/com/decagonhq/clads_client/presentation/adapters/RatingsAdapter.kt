package com.decagonhq.clads_client.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads_client.databinding.SingleRatingRecyclerItemBinding
import com.decagonhq.clads_client.presentation.model.Rating

class RatingsAdapter(
    private var ratingDataSource: List<Rating>
) : RecyclerView.Adapter<RatingsAdapter.RatingViewHolder>() {

    inner class RatingViewHolder(binding: SingleRatingRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var messageTextView = binding.singleRatingDetailsTextView
        private var dateTextView = binding.recyclerRatingDateTextView

        fun bindData(message: Rating) {
            messageTextView.text = message.rating
            dateTextView.text = message.date

            itemView.setOnClickListener {
                // Click code implementation
            }
        }
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {

        holder.bindData(ratingDataSource[position])
    }

    override fun getItemCount(): Int {
        return ratingDataSource.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val binding = SingleRatingRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RatingViewHolder(binding)
    }
}
