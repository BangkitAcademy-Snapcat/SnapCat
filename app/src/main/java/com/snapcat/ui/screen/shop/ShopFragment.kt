package com.snapcat.ui.screen.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.snapcat.databinding.FragmentShopBinding
import com.snapcat.ui.screen.home.JourneyAdapter

class ShopFragment : Fragment() {

    private lateinit var binding: FragmentShopBinding
    private var isFunctionEnabled = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemDecoration = GridSpacingItemDecoration(2, 10, true)

        val layoutManagerShop = GridLayoutManager(requireContext(), 2)
        binding.rvShop.layoutManager = layoutManagerShop
        binding.rvShop.addItemDecoration(itemDecoration)
        binding.rvShop.adapter = ShopAdapter(requireActivity())

        binding.buttonSearch.setOnClickListener {
            // Toggle status on/off
            isFunctionEnabled = !isFunctionEnabled
            binding.linearLayout5.visibility = if (isFunctionEnabled) View.VISIBLE else View.GONE
        }
    }
}
