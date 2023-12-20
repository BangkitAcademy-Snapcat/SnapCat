package com.snapcat.ui.screen.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.snapcat.data.ResultMessage
import com.snapcat.data.ViewModelFactory
import com.snapcat.data.local.preferences.UserDataStore
import com.snapcat.databinding.FragmentProfileBinding
import com.snapcat.ui.dark_mode.ModeFragment
import com.snapcat.ui.screen.about.AboutAppFragment
import com.snapcat.ui.screen.auth.login.LoginDialogFragment
import com.snapcat.ui.screen.journey.JourneyDialogFragment
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var userDataStore: UserDataStore
    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

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

        binding.buttonProfile4.setOnClickListener {
            val modeFragment = ModeFragment()
            modeFragment.show(
                (context as AppCompatActivity).supportFragmentManager,
                "ModeFragment"
            )
        }

        binding.buttonProfile3.setOnClickListener {
            lifecycleScope.launch {
                val loginDialogFragment = LoginDialogFragment()
                loginDialogFragment.show(
                    (context as AppCompatActivity).supportFragmentManager,
                    "LoginDialogFragment"
                )
                userDataStore.deleteSession()

            }
        }

        lifecycleScope.launch {
            userDataStore.getUserData().collect {
                viewModel.getUser(it.token, it.userId).observe(viewLifecycleOwner) {
                    if(it != null){
                        when(it){
                            is ResultMessage.Success -> {
                                binding.username.text = it.data.dataUser.username
                            }

                            else -> {}
                        }
                    }
                }
            }
        }
    }
}
