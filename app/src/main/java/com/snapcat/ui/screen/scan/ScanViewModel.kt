package com.snapcat.ui.screen.scan

import androidx.lifecycle.ViewModel
import com.snapcat.data.SnapCatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(private val repository: SnapCatRepository) : ViewModel() {

}
