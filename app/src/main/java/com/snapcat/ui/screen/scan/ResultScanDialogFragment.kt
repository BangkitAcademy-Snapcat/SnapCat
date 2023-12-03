package com.snapcat.ui.screen.scan

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.snapcat.R
import com.snapcat.databinding.FragmentBottomResultScanBinding
import com.snapcat.ui.screen.auth.verifikasi.VerifikasiDialogFragment

class ResultScanDialogFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private var binding: FragmentBottomResultScanBinding? = null
    var onDialogDismissed: OnDialogDismissListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBottomResultScanBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomSheet()

        val imageUri = arguments?.getString("image_uri")
        binding?.resultScanImg?.setImageURI(Uri.parse(imageUri))
    }

    private fun setupBottomSheet() {
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
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDialogDismissed?.onDialogDismissed()
    }

    private fun showLoading(isLoading: Boolean) {
        // binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}
