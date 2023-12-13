package com.snapcat.ui.screen.auth.register

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.snapcat.R
import com.snapcat.data.ResultMessage
import com.snapcat.data.model.User
import com.snapcat.databinding.FragmentBottomRegisterBinding
import com.snapcat.ui.screen.auth.AuthViewModel
import com.snapcat.util.ToastUtils
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

@AndroidEntryPoint
class RegisterDialogFragment(private val authViewModel: AuthViewModel) : BottomSheetDialogFragment(), View.OnClickListener {

    private var binding: FragmentBottomRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomRegisterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val bottomSheet: FrameLayout = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }
        return dialog
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.register?.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.register -> handleRegister()
        }
    }

    private fun handleRegister() {
        binding?.apply {
            val username = edUsernameRegister.text.toString()
            val email = edEmailRegister.text.toString()
            val password = edPasswordRegister.text.toString()

            val data = User(username = username, email = email, password = password)

            authViewModel.register(data).observe(requireActivity()) { result ->
                handleResult(result)
            }
        }
    }

    private fun handleResult(result: ResultMessage<Response<ResponseBody>>){
        when (result) {
            is ResultMessage.Loading -> {
                showLoading(true)
            }
            is ResultMessage.Success -> {
                ToastUtils.showToast(requireActivity(), "Regsiter berhasil")
                showLoading(false)

            }
            is ResultMessage.Error -> {
                val exception = result.exception
                val errorMessage = exception.message ?: "Regsiter gagal, silahkan coba lagi"
                ToastUtils.showToast(requireContext(), errorMessage)
                showLoading(false)
            }

            else -> {
            }
        }
    }
    private fun showLoading(isLoading: Boolean) {
         binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
