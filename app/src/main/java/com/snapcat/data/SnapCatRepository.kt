package com.snapcat.data

import com.snapcat.data.local.database.SnapCatDao
import com.snapcat.data.remote.retrofit.ApiService
import javax.inject.Inject

class SnapCatRepository @Inject constructor(
    private val apiService: ApiService,
    private val snapCatDao: SnapCatDao,
) {


}