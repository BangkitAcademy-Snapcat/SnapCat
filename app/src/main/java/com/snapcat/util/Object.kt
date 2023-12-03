package com.snapcat.util

import com.snapcat.ui.screen.scan.OnDialogDismissListener
import com.snapcat.ui.screen.scan.ResultScanDialogFragment

class Object {
    companion object {
        fun newInstanceFragmentResultScan(listener: OnDialogDismissListener): ResultScanDialogFragment {
            val fragment = ResultScanDialogFragment()
            fragment.onDialogDismissed = listener
            return fragment
        }
    }
}