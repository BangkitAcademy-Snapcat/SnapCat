package com.snapcat.ui.screen.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.snapcat.R
import com.snapcat.databinding.FragmentBottomCategoriesBinding
import com.snapcat.ui.screen.auth.verifikasi.VerifikasiDialogFragment
import com.snapcat.ui.screen.home.CategoriesAdapter

class CategoriesDialogFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private var binding: FragmentBottomCategoriesBinding? = null
    private var isFunctionEnabled = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBottomCategoriesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheet: FrameLayout =
            dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
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

        val spanCount = 3
        val layoutManagerCategory = GridLayoutManager(requireActivity(), spanCount)
        layoutManagerCategory.orientation = GridLayoutManager.VERTICAL
        binding?.rvCategoriesAll?.layoutManager = layoutManagerCategory
        binding?.rvCategoriesAll?.adapter = CategoriesAdapter(requireActivity())

        binding?.buttonSearch?.setOnClickListener {
            // Toggle status on/off
            isFunctionEnabled = !isFunctionEnabled
            binding?.cardView?.visibility = if (isFunctionEnabled) View.VISIBLE else View.GONE
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
                verifikasiDialog.show(
                    (context as AppCompatActivity).supportFragmentManager,
                    "ForgetDialog"
                )
            }
            R.id.button_search -> {

            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        // binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
