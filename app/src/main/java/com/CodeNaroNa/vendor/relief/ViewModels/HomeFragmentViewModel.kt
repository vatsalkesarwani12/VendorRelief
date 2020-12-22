package com.CodeNaroNa.vendor.relief.ViewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Resource
import com.CodeNaroNa.vendor.relief.Repositories.MainActivityRepository
import com.CodeNaroNa.vendor.relief.modelKotlin.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Suppress("unused")
class HomeFragmentViewModel(private val applicationContext: Context, private val mainActivityRepository: MainActivityRepository) : ViewModel() {


    private val _stateList = MutableLiveData<Resource<ArrayList<String>>>()
    val stateList: LiveData<Resource<ArrayList<String>>>
        get() = _stateList

    private val _cityList = MutableLiveData<Resource<ArrayList<String>>>()
    val cityList: LiveData<Resource<ArrayList<String>>>
        get() = _cityList

    private val _vendorList = MutableLiveData<Resource<ArrayList<UserData>>>()
    val vendorList: LiveData<Resource<ArrayList<UserData>>>
        get() = _vendorList

    val shopCategoryList = arrayListOf("Shop Category", "All", "Grocery", "Dairy", "Medicine", "Others")

    init {
        getStateList()
    }

    private fun getStateList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val stateList = mainActivityRepository.getStateList()
                _stateList.postValue(Resource.Success(data = stateList))
            } catch (e: Exception) {
                _stateList.postValue(Resource.Error(message = "Error Fetching States"))
            }
        }
    }

    fun getCityList(stateName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (stateName == "State")
                    return@launch

                val cityList = mainActivityRepository.getCityList(stateName)
                _cityList.postValue(Resource.Success(data = cityList))
            } catch (e: Exception) {
                _cityList.postValue(Resource.Error(message = "Error Fetching States"))
            }
        }
    }

    fun getVendors(stateName: String, cityName: String, shopCategory: String): Boolean {
        if (stateName == "State" || cityName == "City" || shopCategory == "Shop Category")
            return false

        viewModelScope.launch(Dispatchers.IO) {
            try {
               val list = mainActivityRepository.getVendorData(stateName,cityName, shopCategory)
                if(list.size ==0)
                    throw Exception("No Vendors Found")

                _vendorList.postValue(Resource.Success(data = list))
            } catch (e: Exception) {
               _vendorList.postValue(Resource.Error(message = ""+e.localizedMessage))
            }
        }
        return true
    }
}