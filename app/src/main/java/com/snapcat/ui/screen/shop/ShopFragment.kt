package com.snapcat.ui.screen.shop

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
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

        setUpRecyclerView()

        binding.buttonSearch.setOnClickListener {
            isFunctionEnabled = !isFunctionEnabled
            binding.linearLayout5.visibility = if (isFunctionEnabled) View.VISIBLE else View.GONE
        }
    }

    private fun setUpRecyclerView() {
        val spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2

        val itemDecoration = GridSpacingItemDecoration(spanCount, 10, true)
        val layoutManagerShop = GridLayoutManager(requireContext(), spanCount)
        binding.rvShop.layoutManager = layoutManagerShop
        binding.rvShop.addItemDecoration(itemDecoration)
        binding.rvShop.adapter = ShopAdapter(requireActivity())
    }
}

