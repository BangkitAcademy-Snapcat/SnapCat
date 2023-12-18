package com.snapcat.ui.screen.shop

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.snapcat.data.remote.response.Data
import com.snapcat.databinding.ItemShopBinding


class ShopAdapter: ListAdapter<Data, ShopAdapter.MyViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

    class MyViewHolder(val binding: ItemShopBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data){
            binding.apply{
                titleItemShop.text = data.name
                addressItemShop.text = data.address
                detailJourney.setOnClickListener{
                    it.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.urlShop)))
                }

            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }
}
