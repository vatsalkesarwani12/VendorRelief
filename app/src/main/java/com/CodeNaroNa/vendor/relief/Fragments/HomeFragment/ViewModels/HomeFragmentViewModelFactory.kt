package com.CodeNaroNa.vendor.relief.Fragments.HomeFragment.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.CodeNaroNa.vendor.relief.Repositories.CentralRepository

class HomeFragmentViewModelFactory(private val applicationContext: Context,private val centralRepository: CentralRepository):ViewModelProvider.NewInstanceFactory()
{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(applicationContext,centralRepository) as T
    }
}