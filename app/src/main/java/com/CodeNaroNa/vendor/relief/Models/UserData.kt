package com.CodeNaroNa.vendor.relief.Models

import com.google.firebase.firestore.PropertyName

data class UserData(@get:PropertyName("Shop Name") @set:PropertyName("Shop Name") var shopName: String = "",
                    @get:PropertyName("Shop Category") @set:PropertyName("Shop Category") var shopCategory: String = "",
                    @get:PropertyName("Address") @set:PropertyName("Address") var address: String = "",
                    @get:PropertyName("State") @set:PropertyName("State") var state: String = "",
                    @get:PropertyName("City") @set:PropertyName("City") var city: String = "",
                    @get:PropertyName("Phone Number") @set:PropertyName("Phone Number") var phoneNumber: String = "",
                    @get:PropertyName("Opening Time") @set:PropertyName("Opening Time") var openTime: String = "",
                    @get:PropertyName("Closing Time") @set:PropertyName("Closing Time") var closeTime: String = "")