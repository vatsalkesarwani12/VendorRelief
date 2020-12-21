package com.CodeNaroNa.vendor.relief.ViewModels

import android.content.Context
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Constants
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Resource
import com.CodeNaroNa.vendor.relief.Repositories.MainActivityRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class SignUpActivityViewModel(private val applicationContext: Context, private val mainActivityRepository: MainActivityRepository) : ViewModel() {


    private val _optVerificationState = MutableLiveData<Resource<String>>()
    val optVerificationState: LiveData<Resource<String>>
        get() = _optVerificationState


    private var sentOTP: String = ""
    var modifiedPhoneNumber: String = ""


    fun verifyNumber(phoneNumber: String): Boolean {
        if (phoneNumber.trim().length != 10 || !phoneNumber.isDigitsOnly()) {
            _optVerificationState.postValue(Resource.Error(message = "Improper Phone Number"))
            return false
        }

        this.modifiedPhoneNumber = "+91${phoneNumber.trim()}"

        return true
    }


    fun saveEnteredOTP(sentOTP: String) {
        this.sentOTP = sentOTP
    }

    fun signInUser(enteredOTP: String, userType: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _optVerificationState.postValue(Resource.Loading())
                val credential = PhoneAuthProvider.getCredential(sentOTP, enteredOTP)
                FirebaseAuth.getInstance().signInWithCredential(credential).await()

                if (userType == Constants.EXISTING_USER) {
                    _optVerificationState.postValue(Resource.Success(""))
                    return@launch
                }

                mainActivityRepository.signUpNewVendor(modifiedPhoneNumber)
                _optVerificationState.postValue(Resource.Success(data = ""))
            } catch (e: Exception) {
                _optVerificationState.postValue(Resource.Error(message = "Something went wrong Try again"))
            }
        }
    }
}