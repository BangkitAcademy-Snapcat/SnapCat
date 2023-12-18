package com.snapcat.ui.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snapcat.data.remote.response.DataItem
import com.snapcat.databinding.ItemJourneyBinding


class JourneyAdapter: ListAdapter<DataItem, JourneyAdapter.MyViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemJourneyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

    class MyViewHolder(val binding: ItemJourneyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem){
            binding.apply{
                titleItemJourney.text = data.breed
                timeItemJourney.text = "${data.createdAt.take(10)} - ${data.createdAt.substring(11, 19)}"
                Glide.with(itemView.context)
                    .load(data.image) // URL Gambar
                    .into(imgPhotoJourney)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
