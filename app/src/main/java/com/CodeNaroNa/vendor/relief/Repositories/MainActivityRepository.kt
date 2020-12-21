package com.CodeNaroNa.vendor.relief.Repositories

import com.CodeNaroNa.vendor.relief.GlobalHelpers.Constants
import com.CodeNaroNa.vendor.relief.modelKotlin.PrecautionData
import com.CodeNaroNa.vendor.relief.modelKotlin.UserData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class MainActivityRepository {

    suspend fun getStateList(): ArrayList<String> {
        val stateList = ArrayList<String>()
        stateList.add("State")
        val documents = Firebase.firestore.collection(Constants.STATE_COLLECTION_PATH).get().await()

        documents.forEach { individualDocument ->
            stateList.add(individualDocument.id)
        }

        return stateList
    }

    suspend fun getCityList(stateName: String): ArrayList<String> {
        val cityList = ArrayList<String>()

        val documents = Firebase.firestore.collection(Constants.STATE_COLLECTION_PATH).document(stateName).get().await()
        val map = documents.data!!
        map.forEach { individualMapEntry ->
            cityList.add(individualMapEntry.key)
        }

        cityList.sort()
        cityList.add(0, "City")
        return cityList
    }

    suspend fun getVendorData(stateName: String, cityName: String, shopCategory: String): ArrayList<UserData> {

        val query = if (shopCategory == "All")
            Firebase.firestore.collection(Constants.VENDOR_COLLECTION_PATH)
                    .whereEqualTo(Constants.CITY_PARAMETER, cityName).get().await()
        else Firebase.firestore.collection(Constants.VENDOR_COLLECTION_PATH)
                .whereEqualTo(Constants.CITY_PARAMETER, cityName).whereEqualTo("Shop Category", shopCategory).get().await()

        return query.toObjects(UserData::class.java) as ArrayList<UserData>
    }

    suspend fun signUpNewVendor(phoneNumber:String)
    {
        val data = UserData(phoneNumber = phoneNumber)
        Firebase.firestore.collection(Constants.VENDOR_COLLECTION_PATH).document(phoneNumber).set(data).await()
    }

    suspend fun getPrecautionData() : ArrayList<PrecautionData>
    {
        val data = Firebase.firestore.collection(Constants.DATA_FACTS_COLLECTION).get().await()
        return data.toObjects(PrecautionData::class.java) as ArrayList<PrecautionData>
    }

    suspend fun getVendor(phoneNumber:String) : UserData
    {
        val data =  Firebase.firestore.collection(Constants.VENDOR_COLLECTION_PATH).document(phoneNumber).get().await()
        return data.toObject(UserData::class.java)!!
    }

    suspend fun updateVendor(userData: UserData)
    {
        Firebase.firestore.collection(Constants.VENDOR_COLLECTION_PATH).document(userData.phoneNumber).set(userData).await()
    }
}