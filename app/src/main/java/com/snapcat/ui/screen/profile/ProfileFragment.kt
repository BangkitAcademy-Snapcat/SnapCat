package com.snapcat.ui.screen.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.snapcat.databinding.FragmentProfileBinding
import com.snapcat.databinding.FragmentScanBinding
import com.snapcat.ui.screen.about.AboutAppFragment
import com.snapcat.ui.screen.journey.JourneyDialogFragment

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonProfile1.setOnClickListener {
            val journeyDialog = JourneyDialogFragment()
            journeyDialog.show(
                (context as AppCompatActivity).supportFragmentManager,
                "JourneyDialog"
            )
        }

        binding.buttonProfile2.setOnClickListener {
            val aboutAppFragment = AboutAppFragment()
            aboutAppFragment.show(
                (context as AppCompatActivity).supportFragmentManager,
                "AboutAppFragment"
            )
        }
    }
}
