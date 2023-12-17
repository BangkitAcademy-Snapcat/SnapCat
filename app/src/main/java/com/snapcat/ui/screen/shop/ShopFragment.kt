package com.snapcat.ui.screen.shop

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.snapcat.data.ResultMessage
import com.snapcat.data.ViewModelFactory
import com.snapcat.data.local.preferences.UserDataStore
import com.snapcat.databinding.FragmentShopBinding
import com.snapcat.ui.screen.auth.AuthViewModel
import com.snapcat.ui.screen.home.JourneyAdapter
import com.snapcat.util.ToastUtils
import kotlinx.coroutines.launch

class ShopFragment : Fragment() {

    private lateinit var binding: FragmentShopBinding
    private var isFunctionEnabled = false
    private lateinit var shopAdapter: ShopAdapter
    private lateinit var userDataStore: UserDataStore
    private val viewModel by viewModels<ShopViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shopAdapter = ShopAdapter()

        userDataStore = UserDataStore.getInstance(requireContext())

        binding.buttonSearch.setOnClickListener {
            isFunctionEnabled = !isFunctionEnabled
            binding.linearLayout5.visibility = if (isFunctionEnabled) View.VISIBLE else View.GONE
        }

        lifecycleScope.launch {
            userDataStore.getUserData().collect{
                viewModel.getAllShop(it.token, it.userId).observe(viewLifecycleOwner){
                    if(it != null){
                        when(it){
                            is ResultMessage.Success -> {
                                binding.apply{
                                    rvShop.layoutManager = GridLayoutManager(context, 2)
                                    rvShop.setHasFixedSize(true)
                                    shopAdapter.submitList(it.data.data)
                                    rvShop.adapter = shopAdapter
                                }
                            }

                            is ResultMessage.Error -> {
                            }

                            else -> {}
                        }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        val spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2

//        val itemDecoration = GridSpacingItemDecoration(spanCount, 10, true)
//        val layoutManagerShop = GridLayoutManager(requireContext(), spanCount)
//        binding.rvShop.layoutManager = layoutManagerShop
//        binding.rvShop.addItemDecoration(itemDecoration)
//        binding.rvShop.adapter = ShopAdapter(requireActivity())
    }
}

