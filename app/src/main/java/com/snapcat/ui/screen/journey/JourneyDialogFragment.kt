package com.snapcat.ui.screen.journey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.snapcat.R
import com.snapcat.databinding.FragmentBottomCategoriesBinding
import com.snapcat.databinding.FragmentBottomForgetPasswordBinding
import com.snapcat.databinding.FragmentBottomJourneyBinding
import com.snapcat.ui.screen.auth.verifikasi.VerifikasiDialogFragment
import com.snapcat.ui.screen.home.CategoriesAdapter
import com.snapcat.ui.screen.home.JourneyAdapter

class JourneyDialogFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private var binding: FragmentBottomJourneyBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomJourneyBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheet: FrameLayout = dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
        bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT

        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.apply {
            peekHeight = resources.displayMetrics.heightPixels
            state = BottomSheetBehavior.STATE_EXPANDED

            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {}

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }

        val layoutManagerJourney = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding?.rvJourneyAll?.layoutManager = layoutManagerJourney
        binding?.rvJourneyAll?.adapter = JourneyAdapter(requireActivity())
        binding?.closeLogin?.setOnClickListener {
            dismiss()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.forget -> {
                val verifikasiDialog = VerifikasiDialogFragment()
                verifikasiDialog.show((context as AppCompatActivity).supportFragmentManager, "ForgetDialog")
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        // binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
