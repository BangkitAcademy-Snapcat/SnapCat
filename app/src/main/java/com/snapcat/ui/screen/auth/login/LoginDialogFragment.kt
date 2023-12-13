package com.snapcat.ui.screen.auth.login

import android.app.Dialog
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.snapcat.R
import com.snapcat.data.ResultMessage
import com.snapcat.data.model.User
import com.snapcat.databinding.FragmentBottomLoginBinding
import com.snapcat.ui.screen.MainActivity
import com.snapcat.ui.screen.auth.AuthViewModel
import com.snapcat.ui.screen.auth.forget.ForgetDialogFragment
import com.snapcat.util.ToastUtils
import okhttp3.ResponseBody
import retrofit2.Response

class LoginDialogFragment(private val authViewModel: AuthViewModel) : BottomSheetDialogFragment(), View.OnClickListener {

    private var binding: FragmentBottomLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomLoginBinding.inflate(inflater, container, false)
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
        binding?.forgetPassword?.setOnClickListener(this)
        binding?.login?.setOnClickListener(this)
        binding?.forgetPassword?.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.forget_password -> {
                val forgetDialog = ForgetDialogFragment()
                forgetDialog.show((context as AppCompatActivity).supportFragmentManager, "ForgetDialog")
            }
            R.id.login -> handleLogin()
        }
    }

    private fun handleLogin() {
        binding?.apply {
            val email = edEmailLogin.text.toString()
            val password = edPasswordLogin.text.toString()

            val data = User(email = email, password = password)

            authViewModel.login(data).observe(requireActivity()) { result ->
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
                ToastUtils.showToast(requireActivity(), "Login berhasil")
                showLoading(false)

            }
            is ResultMessage.Error -> {
                val exception = result.exception
                val errorMessage = exception.message ?: "Login gagal, silahkan coba lagi"
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
