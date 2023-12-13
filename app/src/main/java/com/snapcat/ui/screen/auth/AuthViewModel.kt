package com.snapcat.ui.screen.auth

import androidx.lifecycle.ViewModel
import com.snapcat.data.SnapCatRepository
import com.snapcat.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: SnapCatRepository) : ViewModel() {
    fun login(user: User) = repository.login(user)

    fun register(user: User) = repository.register(user)
}
