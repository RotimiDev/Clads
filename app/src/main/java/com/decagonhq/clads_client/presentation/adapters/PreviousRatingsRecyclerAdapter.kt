package com.decagonhq.clads_client.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads_client.databinding.SingleRatingRecyclerItemBinding

class PreviousRatingsRecyclerAdapter(
    private val message: List<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var messageTextView: TextView

    inner class RatingViewHolder(binding: SingleRatingRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            messageTextView = binding.ratingDetailsTextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = SingleRatingRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RatingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.apply {
            messageTextView.text = message[position]
        }
    }

    override fun getItemCount(): Int {
        return message.size
    }
}
