package com.CodeNaroNa.vendor.relief.ViewModels.viewModelfactories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.CodeNaroNa.vendor.relief.Repositories.MainActivityRepository
import com.CodeNaroNa.vendor.relief.ViewModels.HomeFragmentViewModel

class HomeFragmentViewModelFactory(private val applicationContext: Context,private val mainActivityRepository: MainActivityRepository):ViewModelProvider.NewInstanceFactory()
{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(applicationContext,mainActivityRepository) as T
    }
}