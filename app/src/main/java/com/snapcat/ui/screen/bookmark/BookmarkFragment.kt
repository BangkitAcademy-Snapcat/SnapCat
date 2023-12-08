package com.snapcat.ui.screen.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.snapcat.databinding.FragmentBookmarkBinding

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding
    private var isFunctionEnabled = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.buttonSearch?.setOnClickListener {
            // Toggle status on/off
            isFunctionEnabled = !isFunctionEnabled
            binding?.linearLayout5?.visibility = if (isFunctionEnabled) View.VISIBLE else View.GONE
        }
    }
}
