package com.CodeNaroNa.vendor.relief.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.CodeNaroNa.vendor.relief.Repositories.MainActivityRepository

class HomeFragmentViewModelFactory(private val applicationContext: Context,private val mainActivityRepository: MainActivityRepository):ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(applicationContext,mainActivityRepository) as T
    }
}