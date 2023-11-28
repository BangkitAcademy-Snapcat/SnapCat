package com.snapcat.ui.screen.history

import androidx.lifecycle.ViewModel
import com.snapcat.data.SnapCatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: SnapCatRepository) : ViewModel() {

}
