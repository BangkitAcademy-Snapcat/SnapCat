package com.snapcat.ui.screen.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.snapcat.R
import com.snapcat.databinding.ContentOnBoardingBinding
import com.snapcat.databinding.ItemCategoriesBinding
import com.snapcat.databinding.ItemJourneyBinding
import com.snapcat.ui.screen.auth.login.LoginDialogFragment
import com.snapcat.ui.screen.auth.register.RegisterDialogFragment


class JourneyAdapter(private val context: Context) : RecyclerView.Adapter<JourneyAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemJourneyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(ItemJourneyBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {

        }
    }
    override fun getItemCount() = 4
}
