package com.snapcat.ui.screen.detail

import androidx.lifecycle.ViewModel
import com.snapcat.data.SnapCatRepository
import com.snapcat.data.model.History
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class DetailViewModel(private val repository: SnapCatRepository) : ViewModel() {
    fun saveToHistory(token: String, history: History) = repository.saveToHistory(token, history)
}
