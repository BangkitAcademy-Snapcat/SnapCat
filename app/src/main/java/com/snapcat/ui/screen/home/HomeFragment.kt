package com.snapcat.ui.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.snapcat.databinding.FragmentHomeBinding
import com.snapcat.ui.screen.category.CategoriesDialogFragment
import com.snapcat.ui.screen.journey.JourneyDialogFragment

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManagerCategory =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.layoutManager = layoutManagerCategory
        binding.rvCategories.adapter = CategoriesAdapter(requireActivity())

        val layoutManagerJourney =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvJourney.layoutManager = layoutManagerJourney
        binding.rvJourney.adapter = JourneyAdapter(requireActivity())
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


    }
}
