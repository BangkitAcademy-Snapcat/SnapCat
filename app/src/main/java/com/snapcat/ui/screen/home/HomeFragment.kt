package com.snapcat.ui.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.snapcat.R
import com.snapcat.data.ResultMessage
import com.snapcat.data.ViewModelFactory
import com.snapcat.data.local.preferences.UserDataStore
import com.snapcat.data.model.CatCategory
import com.snapcat.databinding.FragmentHomeBinding
import com.snapcat.ui.screen.auth.AuthViewModel
import com.snapcat.ui.screen.category.CategoriesDialogFragment
import com.snapcat.ui.screen.journey.JourneyDialogFragment
import com.snapcat.ui.screen.journey.JourneyViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var userDataStore: UserDataStore
    private lateinit var journeyAdapter: JourneyAdapter
    private val list = ArrayList<CatCategory>()
    private val viewModel by viewModels<JourneyViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }
    private val viewModel2 by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        journeyAdapter = JourneyAdapter()
        userDataStore = UserDataStore.getInstance(requireContext())

        lifecycleScope.launch {
            userDataStore.getUserData().collect{
                binding.username.text = it.username
            }
        }
        list.addAll(getListHeroes())
        val layoutManagerCategory =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.layoutManager = layoutManagerCategory
        binding.rvCategories.adapter = CategoriesAdapter(list)

        val layoutManagerJourney =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvJourney.layoutManager = layoutManagerJourney
        binding.rvJourney.adapter = JourneyAdapter()
        binding.rvJourney.isNestedScrollingEnabled = false

        binding.showAllCategories.setOnClickListener {
            val categoriesDialog = CategoriesDialogFragment()
            categoriesDialog.show(
                (context as AppCompatActivity).supportFragmentManager,
                "CategoriesDialog"
            )
        }
        binding.showAllJourney.setOnClickListener {
            val journeyDialog = JourneyDialogFragment()
            journeyDialog.show(
                (context as AppCompatActivity).supportFragmentManager,
                "JourneyDialog"
            )
        }
        lifecycleScope.launch {
            userDataStore.getUserData().collect {
                viewModel.getAllHistories(it.token, it.userId).observe(viewLifecycleOwner) {
                    if (it != null) {
                        when (it) {
                            is ResultMessage.Success -> {
                                binding.apply {
                                    rvJourney.layoutManager = LinearLayoutManager(context)
                                    rvJourney.setHasFixedSize(true)
                                    journeyAdapter.submitList(it.data.dataItem)
                                    rvJourney.adapter = journeyAdapter
                                }
                            }
                            is ResultMessage.Error -> {
                                // Handle error case if needed
                            }
                            else -> {
                                // Handle other cases if needed
                            }
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            userDataStore.getUserData().collect{ datastore ->
                viewModel2.getUser(datastore.token, datastore.userId).observe(viewLifecycleOwner){ user ->
                    if(user != null){
                        when(user){
                            is ResultMessage.Success -> {
                                Glide.with(requireActivity())
                                    .load(user.data.dataUser.urlProfile)
                                    .into(binding.profileImage)
                            }

                            else -> {}
                        }
                    }
                }
            }
        }
    }

    private fun getListHeroes(): ArrayList<CatCategory> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listHero = ArrayList<CatCategory>()
        for (i in dataName.indices) {
            val hero = CatCategory(dataName[i], dataPhoto.getResourceId(i, -1))
            listHero.add(hero)
        }
        return listHero
    }
}
