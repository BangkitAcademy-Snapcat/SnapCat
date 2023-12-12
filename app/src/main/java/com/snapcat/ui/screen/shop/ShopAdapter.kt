package com.snapcat.ui.screen.shop

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.snapcat.databinding.ItemJourneyBinding
import com.snapcat.databinding.ItemShopBinding
import com.snapcat.ui.screen.journey.DetailJourneyDialogFragment


class ShopAdapter(private val context: Context) :
    RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemShopBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(ItemShopBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            root.setOnClickListener {
            }

        }


    }

    override fun getItemCount() = 10
}
