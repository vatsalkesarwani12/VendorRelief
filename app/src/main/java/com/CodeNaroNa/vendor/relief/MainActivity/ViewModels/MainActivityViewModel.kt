package com.CodeNaroNa.vendor.relief.MainActivity.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Resource
import com.CodeNaroNa.vendor.relief.Repositories.CentralRepository
import com.CodeNaroNa.vendor.relief.Models.PrecautionData
import com.CodeNaroNa.vendor.relief.Models.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    lateinit var selectedUserData: UserData

    private val _precautionData = MutableLiveData<Resource<ArrayList<PrecautionData>>>()
    val precautionData: LiveData<Resource<ArrayList<PrecautionData>>>
        get() = _precautionData


    fun getPrecautionData()
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val repo = CentralRepository()
                val list = repo.getPrecautionData()
                _precautionData.postValue(Resource.Success(data = list))
            }catch (e:Exception)
            {
                _precautionData.postValue(Resource.Error(message = ""+e.localizedMessage!!))
            }
        }
    }
}