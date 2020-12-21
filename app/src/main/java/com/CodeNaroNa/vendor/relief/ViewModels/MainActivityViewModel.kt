package com.CodeNaroNa.vendor.relief.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Resource
import com.CodeNaroNa.vendor.relief.Repositories.MainActivityRepository
import com.CodeNaroNa.vendor.relief.modelKotlin.PrecautionData
import com.CodeNaroNa.vendor.relief.modelKotlin.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel : ViewModel() {
    lateinit var selectedUserData: UserData

    private val _precautionData = MutableLiveData<Resource<ArrayList<PrecautionData>>>()
    val precautionData: LiveData<Resource<ArrayList<PrecautionData>>>
        get() = _precautionData


    fun getPrecautionData()
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val repo = MainActivityRepository()
                val list = repo.getPrecautionData()
                _precautionData.postValue(Resource.Success(data = list))
            }catch (e:Exception)
            {
                _precautionData.postValue(Resource.Error(message = ""+e.localizedMessage!!))
            }
        }
    }
}