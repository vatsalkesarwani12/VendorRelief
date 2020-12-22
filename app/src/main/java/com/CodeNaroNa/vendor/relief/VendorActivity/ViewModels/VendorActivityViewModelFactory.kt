package com.CodeNaroNa.vendor.relief.VendorActivity.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.CodeNaroNa.vendor.relief.Repositories.CentralRepository

class VendorActivityViewModelFactory(private val applicationContext: Context, private val centralRepository: CentralRepository) :ViewModelProvider.NewInstanceFactory()
{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VendorActivityViewModel(applicationContext,centralRepository) as T
    }
}