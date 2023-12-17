package com.snapcat.ui.screen.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.snapcat.data.local.preferences.UserDataStore
import com.snapcat.databinding.FragmentProfileBinding
import com.snapcat.ui.screen.about.AboutAppFragment
import com.snapcat.ui.screen.journey.JourneyDialogFragment
import com.snapcat.ui.screen.welcome.Welcome
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var userDataStore: UserDataStore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userDataStore = UserDataStore.getInstance(requireContext())

        lifecycleScope.launch {
            userDataStore.getUserData().collect{
                binding.username.text = it.username
            }
        }
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

        binding.buttonProfile3.setOnClickListener {
            lifecycleScope.launch {
                userDataStore.deleteSession()
                startActivity(Intent(requireActivity(), Welcome::class.java))
                requireActivity().finish()
            }
        }

    }
}
