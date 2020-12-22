package com.CodeNaroNa.vendor.relief.SignUpActivity.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.CodeNaroNa.vendor.relief.Repositories.CentralRepository

class SignUpActivityViewModelFactory(private val applicationContext: Context, private val centralRepository: CentralRepository) : ViewModelProvider.NewInstanceFactory()
{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpActivityViewModel(applicationContext,centralRepository) as T
    }
}