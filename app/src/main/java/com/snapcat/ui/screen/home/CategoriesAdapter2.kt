package com.snapcat.ui.screen.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.snapcat.R
import com.snapcat.data.model.CatCategory
import com.snapcat.databinding.ItemCategoriesBinding


class CategoriesAdapter2(private val listCatCategory: MutableList<CatCategory>) : RecyclerView.Adapter<CategoriesAdapter2.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_categories2, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, photo) = listCatCategory[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
    }
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_title)
    }

    override fun getItemCount() = listCatCategory.size
}
