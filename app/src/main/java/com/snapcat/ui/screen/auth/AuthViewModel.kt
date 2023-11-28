package com.snapcat.ui.screen.auth

import androidx.lifecycle.ViewModel
import com.snapcat.data.SnapCatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: SnapCatRepository) : ViewModel() {

}
