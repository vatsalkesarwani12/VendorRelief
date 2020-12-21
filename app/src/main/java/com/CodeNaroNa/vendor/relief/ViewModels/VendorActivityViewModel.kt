package com.CodeNaroNa.vendor.relief.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Constants
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Resource
import com.CodeNaroNa.vendor.relief.Repositories.MainActivityRepository
import com.CodeNaroNa.vendor.relief.modelKotlin.PrecautionData
import com.CodeNaroNa.vendor.relief.modelKotlin.UserData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VendorActivityViewModel(private val applicationContext: Context, private val mainActivityRepository: MainActivityRepository) : ViewModel() {
    private val _stateList = MutableLiveData<Resource<ArrayList<String>>>()
    val stateList: LiveData<Resource<ArrayList<String>>>
        get() = _stateList

    private val _cityList = MutableLiveData<Resource<ArrayList<String>>>()
    val cityList: LiveData<Resource<ArrayList<String>>>
        get() = _cityList

    val shopCategoryList = Constants.SHOP_CATEGORY
    val timeList = arrayOf("Select", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
    val amPmList = arrayOf("Select", "AM", "PM")

    private val _vendor = MutableLiveData<Resource<UserData>>()
    val vendor: LiveData<Resource<UserData>>
        get() = _vendor

    private val _precautionData = MutableLiveData<Resource<ArrayList<PrecautionData>>>()
    val precautionData: LiveData<Resource<ArrayList<PrecautionData>>>
        get() = _precautionData

    private val _saveDataState = MutableLiveData<Resource<String>>()
    val saveDataState: LiveData<Resource<String>>
        get() = _saveDataState

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

    fun getVendor(phoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data2 = mainActivityRepository.getVendor(phoneNumber)
                Log.d("SSSS", data2.toString())
                _vendor.postValue(Resource.Success(data = data2))
            } catch (e: Exception) {

            }
        }
    }

    fun getPrecautionData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list = mainActivityRepository.getPrecautionData()
                _precautionData.postValue(Resource.Success(data = list))
            } catch (e: java.lang.Exception) {
                _precautionData.postValue(Resource.Error(message = "" + e.localizedMessage!!))
            }
        }
    }

    fun saveData(shopName: String, shopCategory: String, state: String, city: String, address: String, openTime: String, closeTime: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (checkValidity(shopName, shopCategory, state, city, address, openTime, closeTime)) {
                    val userData = UserData(shopName, shopCategory, address, state, city,
                            FirebaseAuth.getInstance().currentUser?.phoneNumber!!, openTime, closeTime)

                    _saveDataState.postValue(Resource.Loading())
                    mainActivityRepository.updateVendor(userData)
                    _saveDataState.postValue(Resource.Success(data = "Updated"))
                }
            } catch (e: Exception) {
                _saveDataState.postValue(Resource.Error(message = "" + e.localizedMessage))
            }
        }
    }

    private fun checkValidity(shopName: String, shopCategory: String, state: String, city: String, address: String, openTime: String, closeTime: String): Boolean {
        if (shopName.length <= 3)
            throw Exception("Invalid Shop Name")

        if (shopCategory == "Shop Category")
            throw Exception("Invalid Shop Category")

        if (state == "State" || state.isEmpty())
            throw Exception("Invalid State")

        if (city == "City" || city.isEmpty())
            throw Exception("Invalid City")

        if (address.length <= 3)
            throw Exception("Invalid Address")

        if (openTime.contains("Select"))
            throw Exception("Invalid Open Time")

        if (closeTime.contains("Select"))
            throw Exception("Invalid Close Time")

        return true
    }
}